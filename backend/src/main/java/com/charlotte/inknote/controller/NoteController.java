package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.service.NoteService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
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
    public NoteDTO addNote(@RequestBody NoteDTO noteDTO) {
        return noteService.save(noteDTO);
    }

    @PutMapping("/update")
    public NoteDTO updateNote(@RequestBody NoteDTO noteDTO) {
        return noteService.update(noteDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNote(@PathVariable Long id) {
        noteService.delete(id);
    }

    @GetMapping("/home")
    public String home(Principal principal) {
        return "Hello, " + principal.getName();
    }


}
