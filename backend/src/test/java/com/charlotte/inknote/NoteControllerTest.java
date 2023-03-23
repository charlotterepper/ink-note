package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.service.NoteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private NoteDTOMapper noteDTOMapper;

    @Test
    void testAllNotes() throws Exception {
        List<NoteDTO> expected = List.of(new NoteDTO("hello", "world"));

        when(noteService.findAll()).thenReturn(expected);

        mvc.perform(get("/notes/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(expected.get(0).getTitle())))
                .andExpect(jsonPath("$[0].description", is(expected.get(0).getDescription())));
    }

    @Test
    void testAddNote() throws Exception {
        NoteDTO noteDTO = new NoteDTO("hello", "world");
        ObjectMapper objectMapper = new ObjectMapper();

        when(noteService.save(any())).thenReturn(noteDTO);

        mvc.perform(post("/notes/add")
                .content(objectMapper.writeValueAsString(noteDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is(noteDTO.getTitle())))
                .andExpect(jsonPath("$.description", is(noteDTO.getDescription())));
    }
}
