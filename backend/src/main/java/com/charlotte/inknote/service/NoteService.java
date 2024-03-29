package com.charlotte.inknote.service;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.mapper.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public NoteDTO update(NoteDTO noteDTO) {
        Note newNote = noteDTOMapper.toNote(noteDTO);
        Note savedNote = noteRepository.save(newNote);
        return noteDTOMapper.toNoteDTO(savedNote);
    }

    public void delete(Long id) {
        Note note = noteRepository.findById(id).orElseThrow();
        noteRepository.delete(note);
    }

    public List<NoteDTO> findByUserId(Long userId) {
        List<Note> notes = noteRepository.findByUserId(userId);
        return noteDTOMapper.toNoteDTOList(notes);
    }
}
