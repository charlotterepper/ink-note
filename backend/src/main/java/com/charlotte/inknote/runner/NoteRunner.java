package com.charlotte.inknote.runner;

import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class NoteRunner implements CommandLineRunner {
    private final NoteRepository noteRepository;

    @Override
    public void run(String... args) throws Exception {
        Stream.of("Note1", "Note2", "Note3", "Note4", "Note5").forEach(title -> {
            Note note = new Note(title, "Hello " + title);
            noteRepository.save(note);
        });
//        noteService.findAll().forEach(System.out::println);
    }
}
