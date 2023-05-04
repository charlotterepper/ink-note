package com.charlotte.inknote;

import com.charlotte.inknote.controller.NoteController;
import com.charlotte.inknote.controller.UserController;
import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.UserDTO;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.NoteService;
import com.charlotte.inknote.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser() // username "user", password "password", roles "ROLE_USER"
    void testAllNotes() throws Exception {
        String expected = "USER";
        String principalEmail = "user@mail.com";

        when(userService.getUserRole(principalEmail)).thenReturn("USER");

        mvc.perform(get("/user/role/" + principalEmail)
                        .with(SecurityMockMvcRequestPostProcessors.jwt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(expected));
    }
}
