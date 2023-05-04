package com.charlotte.inknote;

import com.charlotte.inknote.controller.NoteController;
import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.UserDTO;
import com.charlotte.inknote.mapper.UserAdminDTOMapper;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.NoteService;
import com.charlotte.inknote.service.TokenService;
import com.charlotte.inknote.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = NoteController.class)
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser() // username "user", password "password", roles "ROLE_USER"
    void testAllNotes() throws Exception {
        List<NoteDTO> expected = List.of(new NoteDTO(1L, "hello", "world", new UserDTO()));

        when(userService.findByEmail(any())).thenReturn(new User());
        when(noteService.findByUserId(any())).thenReturn(expected);

        mvc.perform(get("/notes/all")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(expected.get(0).getId()), Long.class))
                .andExpect(jsonPath("$[0].title", is(expected.get(0).getTitle())))
                .andExpect(jsonPath("$[0].description", is(expected.get(0).getDescription())));
    }

    @Test
    @WithMockUser()
    void testAddNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO(1L, "hello", "world", new UserDTO());
        ObjectMapper objectMapper = new ObjectMapper();

        when(userService.findByEmail(any())).thenReturn(new User());
        when(noteService.save(any())).thenReturn(noteDTO);

        mvc.perform(post("/notes/add")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .content(objectMapper.writeValueAsString(noteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(noteDTO.getId()), Long.class))
                .andExpect(jsonPath("$.title", is(noteDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(noteDTO.getDescription())));
    }

    @Test
    @WithMockUser()
    void testUpdateNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO(1L, "hello", "world", new UserDTO());
        ObjectMapper objectMapper = new ObjectMapper();

        when(userService.findByEmail(any())).thenReturn(new User());
        when(noteService.update(any())).thenReturn(noteDTO);

        mvc.perform(put("/notes/update")
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .content(objectMapper.writeValueAsString(noteDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(noteDTO.getId()), Long.class))
                .andExpect(jsonPath("$.title", is(noteDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(noteDTO.getDescription()))
        );
    }

    @Test
    @WithMockUser()
    void testDeleteNote() throws Exception {
        mvc.perform(delete("/notes/delete/" + 1)
                        .with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk());
    }


}
