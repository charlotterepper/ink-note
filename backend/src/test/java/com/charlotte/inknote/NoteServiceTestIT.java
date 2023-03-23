package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    void testAddNote() {
        NoteDTO expected = new NoteDTO("hello", "world");

        NoteDTO actual = noteService.save(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void testAllNotes() {
        List<NoteDTO> expected = List.of(new NoteDTO("hello", "world"));

        noteService.save(new NoteDTO("hello", "world"));
        List<NoteDTO> actual = noteService.findAll();

        assertEquals(expected.get(0).getTitle(), actual.get(actual.size() - 1).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(actual.size() - 1).getDescription());
    }
}
