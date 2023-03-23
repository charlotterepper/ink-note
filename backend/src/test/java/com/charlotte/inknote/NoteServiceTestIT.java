package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class NoteServiceTestIT {
    @Autowired
    private NoteService noteService;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databaseabc");
    }

    @Test
    void testAllNotes() {
        List<NoteDTO> expected = List.of(new NoteDTO(6L, "hello", "world"));

        noteService.save(new NoteDTO(6L, "hello", "world"));
        List<NoteDTO> actual = noteService.findAll();

        int lastIndex = actual.size() - 1;

        assertEquals(expected.get(0).getTitle(), actual.get(lastIndex).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(lastIndex).getDescription());
    }

    @Test
    void testAddNote() {
        NoteDTO expected = new NoteDTO(1L, "hello", "world");

        NoteDTO actual = noteService.save(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void testUpdateNote() {
        NoteDTO expected = new NoteDTO(1L, "hello", "world");

        NoteDTO actual = noteService.update(expected, expected.getId());

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

//    @Test
//    void testDeleteNote() {
//        NoteDTO noteDto = new NoteDTO(1L, "hello", "world");
//        noteService.save(noteDto);
//
//        noteService.delete(1L);
//
//        assertTrue()
//    }
}
