package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AddNoteTest {
    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private NoteDTOMapper noteDTOMapper;

    @Autowired
    private NoteService noteService;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databasexyz");
    }

    @Test
    void testAddNote() {
        NoteDTO expected = new NoteDTO("hello", "world");
        Note note = new Note(1L, "hello", "world");
        Note noteNoId = new Note("hello", "world");
        when(noteRepository.save(any())).thenReturn(note);
        when(noteDTOMapper.toNote(expected)).thenReturn(noteNoId);
        when(noteDTOMapper.toNoteDTO(note)).thenReturn(expected);

        NoteDTO actual = noteService.save(expected);

        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getDescription(), expected.getDescription());
    }
}
