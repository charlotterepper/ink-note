package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class NoteServiceTestIT {
    @Autowired
    private NoteService noteService;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databasexyz");
    }

    @Test
    void testAddNote() {
        NoteDTO expected = new NoteDTO("hello", "world");

        NoteDTO actual = noteService.save(expected);

        assertEquals(actual.getTitle(), expected.getTitle());
        assertEquals(actual.getDescription(), expected.getDescription());
    }
}
