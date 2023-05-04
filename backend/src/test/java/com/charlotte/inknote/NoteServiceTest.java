package com.charlotte.inknote;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.mapper.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.service.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceTest {
    @MockBean
    private NoteRepository noteRepository;

    @MockBean
    private NoteDTOMapper noteDTOMapper;

    @Autowired
    private NoteService noteService;

    static {
        System.setProperty("PSQL_USERNAME", "charlotte");
        System.setProperty("PSQL_PASSWORD", "databaseabc");
    }

    @Test
    void testAddNote() {
        NoteDTO expected = new NoteDTO(1L, "hello", "world");
        Note note = new Note(1L, "hello", "world");
        Note noteNoId = new Note("hello", "world");
        when(noteRepository.save(any())).thenReturn(note);
        when(noteDTOMapper.toNote(expected)).thenReturn(noteNoId);
        when(noteDTOMapper.toNoteDTO(note)).thenReturn(expected);

        NoteDTO actual = noteService.save(expected);

        assertEquals(expected.getTitle(), actual.getTitle());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void testFindAllNotes() {
        List<Note> notes = List.of(new Note(1L, "hello", "world"));
        List<NoteDTO> expected = List.of(new NoteDTO(1L, "hello", "world"));
        when(noteRepository.findAll()).thenReturn(notes);
        when(noteDTOMapper.toNoteDTOList(any())).thenReturn(expected);

        List<NoteDTO> actual = noteService.findAll();

        assertEquals(expected.get(0).getTitle(), actual.get(0).getTitle());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void testUpdateNote() {
        Note note = new Note(1L, "old title", "old description");
        NoteDTO noteDTO = new NoteDTO(1L, "new title", "new description");
        when(noteRepository.save(any())).thenReturn(note);
        when(noteDTOMapper.toNote(noteDTO)).thenReturn(note);
        when(noteDTOMapper.toNoteDTO(note)).thenReturn(noteDTO);

        NoteDTO actual = noteService.update(noteDTO);

        assertEquals(noteDTO.getTitle(), actual.getTitle());
        assertEquals(noteDTO.getDescription(), actual.getDescription());
    }
}
