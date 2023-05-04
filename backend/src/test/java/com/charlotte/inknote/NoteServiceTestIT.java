package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.UserDTO;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.model.Role;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.repository.UserRepository;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class NoteServiceTestIT {
    @Autowired
    private NoteService noteService;

    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databaseabc");
    }

    @Test
    void testAllNotes() {
        // Arrange
        List<NoteDTO> expected = List.of(new NoteDTO(1L, "hello", "world", new UserDTO()));
        User user = new User();
        userRepository.save(user);
        List<Note> notes = List.of(new Note(null, "hello", "world", user));
        noteRepository.saveAll(notes);

        // Act
        List<NoteDTO> actual = noteService.findAll();

        // Assert
        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
        assertEquals(expected.get(0).getUserDTO().getEmail(), actual.get(0).getUserDTO().getEmail());
    }

    @Test
    void testAddNote() {
        // Arrange
        User user = new User(1L, "John", "Doe", "user@mail.com", "password", Role.USER, Set.of());
        userRepository.save(user);
        NoteDTO expected = new NoteDTO(1L, "hello", "world", new UserDTO(user.getEmail()));

        // Act
        NoteDTO actual = noteService.save(expected);

        // Assert
        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());

        Note addedNote = noteRepository.findById(1L).get();

        assertEquals(expected.getTitle(), addedNote.getTitle());
        assertEquals(expected.getDescription(), addedNote.getDescription());
        assertEquals(expected.getUserDTO().getEmail(), actual.getUserDTO().getEmail());
    }

    @Test
    void testUpdateNote() {
        User user = new User(1L, "John", "Doe", "user@mail.com", "password", Role.USER, Set.of());
        userRepository.save(user);
        Note note = new Note(null, "old", "old note", user);
        NoteDTO expected = new NoteDTO(1L, "hello", "world", new UserDTO(user.getEmail()));
        noteRepository.save(note);

        NoteDTO actual = noteService.update(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());

        Note updatedNote = noteRepository.findById(1L).get();

        assertEquals(expected.getTitle(), updatedNote.getTitle());
        assertEquals(expected.getDescription(), updatedNote.getDescription());
        assertEquals(expected.getUserDTO().getEmail(), actual.getUserDTO().getEmail());
    }

    @Test
    void testDeleteNote() {
        noteRepository.deleteAll();
        User user = new User(1L, "John", "Doe", "user@mail.com", "password", Role.USER, Set.of());
        userRepository.save(user);
        Note note = new Note(null, "test title", "test description", user);
        noteRepository.save(note);
        System.out.println("noteRepo size: " + noteRepository.findAll().size());
        System.out.println("noteRepo findAll: " + noteRepository.findAll());

        noteService.delete(3L);

        assertEquals(0, noteRepository.findAll().size());
    }
}
