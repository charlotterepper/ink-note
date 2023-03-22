package com.charlotte.inknote;

import com.charlotte.inknote.controller.NoteController;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class NoteControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @Test
    void testAllNotes() throws Exception {
        Note note = new Note("hello", "world");
        List<Note> allNotes = List.of(note);

        given(noteService.findAll()).willReturn(allNotes);

        mvc.perform(get("/notes/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].title", is(note.getTitle())))
                .andExpect(jsonPath("$[0].description", is(note.getDescription())));


    }
}
