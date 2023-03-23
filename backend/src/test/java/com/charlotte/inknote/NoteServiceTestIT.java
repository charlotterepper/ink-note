package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@ActiveProfiles("test")
@SpringBootTest
public class NoteServiceTestIT {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databaseabc");
    }

    @Test
    void testAllNotes() {
        // Arrange
        List<NoteDTO> expected = List.of(new NoteDTO(1L, "hello", "world"));
        List<Note> notes = List.of(new Note(null, "hello", "world"));
        noteRepository.saveAll(notes);

        // Act
        List<NoteDTO> actual = noteService.findAll();

        // Assert
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void testAddNote() {
        // Arrange
        NoteDTO expected = new NoteDTO(1L, "hello", "world");

        // Act
        NoteDTO actual = noteService.save(expected);

        // Assert
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());

        Note addedNote = noteRepository.findById(1L).get();

        assertEquals(expected.getTitle(), addedNote.getTitle());
        assertEquals(expected.getDescription(), addedNote.getDescription());
    }

    @Test
    void testUpdateNote() {
        Note note = new Note(null, "old", "old note");
        NoteDTO expected = new NoteDTO(1L, "hello", "world");
        noteRepository.save(note);

        NoteDTO actual = noteService.update(expected, expected.getId());

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());

        Note updatedNote = noteRepository.findById(1L).get();

        assertEquals(expected.getTitle(), updatedNote.getTitle());
        assertEquals(expected.getDescription(), updatedNote.getDescription());

    }

    @Test
    void testDeleteNote() {
        Note note = new Note(null, "hello", "world");
        noteRepository.save(note);

        noteService.delete(1L);

        assertEquals(0, noteRepository.findAll().size());
    }
}
