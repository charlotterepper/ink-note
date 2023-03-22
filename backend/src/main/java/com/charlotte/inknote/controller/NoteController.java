package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/notes")
public class NoteController {
    public final NoteService noteService;
    public final NoteDTOMapper noteDTOMapper;

    public NoteController(NoteService noteService, NoteDTOMapper noteDTOMapper) {
        this.noteService = noteService;
        this.noteDTOMapper = noteDTOMapper;
    }

    @GetMapping("/all")
    public List<NoteDTO> allNotes() {
        return noteService.findAll();
    }

    @PostMapping("/add")
    public void addNote(@RequestBody NoteDTO noteDTO) {
        noteService.save(noteDTO);
    }
}
