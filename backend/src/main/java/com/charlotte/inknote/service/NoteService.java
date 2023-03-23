package com.charlotte.inknote.service;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final NoteDTOMapper noteDTOMapper;

    public NoteService(NoteRepository noteRepository, NoteDTOMapper noteDTOMapper) {
        this.noteRepository = noteRepository;
        this.noteDTOMapper = noteDTOMapper;
    }

    public List<NoteDTO> findAll() {
        List<Note> notes = noteRepository.findAll();
        return noteDTOMapper.toNoteDTOList(notes);
    }

    public NoteDTO save(NoteDTO noteDTO) {
        Note note = noteDTOMapper.toNote(noteDTO);
        Note savedNote = noteRepository.save(note);
        return noteDTOMapper.toNoteDTO(savedNote);
    }

    public NoteDTO update(NoteDTO noteDTO, Long id) {
        Note newNote = noteDTOMapper.toNote(noteDTO);
        noteRepository
                .findById(id)
                .ifPresent(note -> {
                    note.setTitle(newNote.getTitle());
                    note.setDescription(newNote.getDescription());
                    noteRepository.save(note);
                });
        Note savedNote = noteRepository.findById(id).orElseThrow();
        return noteDTOMapper.toNoteDTO(savedNote);
    }
}
