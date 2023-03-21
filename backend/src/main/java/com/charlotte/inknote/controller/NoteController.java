package com.charlotte.inknote.controller;

import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notes")
public class NoteController {
    public final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/all")
    public List<Note> allNotes() {
        return noteService.findAll();
    }

    @PostMapping("/add")
    public void addNote(@RequestBody Note note) {
        noteService.save(note);
    }
}
