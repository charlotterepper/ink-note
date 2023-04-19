package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import com.charlotte.inknote.service.NoteService;
import com.charlotte.inknote.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<NoteDTO> allNotes(Principal principal) {
        String userEmail = principal.getName();
        Long userId = userService.findByEmail(userEmail).getId();
        return noteService.findByUserId(userId);
    }

    @PostMapping("/add")
    public NoteDTO addNote(@RequestBody NoteDTO noteDTO) {
        return noteService.save(noteDTO);
    }

    @PutMapping("/update")
    public NoteDTO updateNote(@RequestBody NoteDTO noteDTO) {
        System.out.println("NoteDTO: " + noteDTO.toString());
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
