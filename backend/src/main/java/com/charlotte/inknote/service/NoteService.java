package com.charlotte.inknote.service;

import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public void save(Note note) {
        noteRepository.save(note);
    }
}
