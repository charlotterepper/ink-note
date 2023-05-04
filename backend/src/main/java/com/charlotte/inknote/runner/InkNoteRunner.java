package com.charlotte.inknote.runner;

import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.model.Role;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class InkNoteRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        List<Note> notes = createNotes();
        List<User> users = createUsers();
        connectUsersAndNotes(notes, users);
        userRepository.saveAll(users);
    }

    private void connectUsersAndNotes(List<Note> notes, List<User> users) {
        for (int i = 0; i < notes.size(); i++) {
            // Add 2 notes to user 0
            if (i < 2) {
                notes.get(i).setUser(users.get(0));
                users.get(0).getNotes().add(notes.get(i));
            // Add 3 notes to user 1
            } else {
                notes.get(i).setUser(users.get(1));
                users.get(1).getNotes().add(notes.get(i));
            }
        }
    }

    public List<Note> createNotes() {
        return List.of(
                new Note(null, "Note1", "Hello Note1", null),
                new Note(null, "Note2", "Hello Note2", null),
                new Note(null, "Note3", "Hello Note3", null),
                new Note(null, "Note4", "Hello Note4", null),
                new Note(null, "Note5", "Hello Note5", null)
        );
    }

    public List<User> createUsers() {
        User user = new User(
                null,
                "John",
                "Doe",
                "user@mail.com",
                passwordEncoder.encode("password"),
                Role.USER,
                new HashSet<>()
        );
        User admin = new User(
                null,
                "Jane",
                "Doe",
                "admin@mail.com",
                passwordEncoder.encode("password"),
                Role.ADMIN,
                new HashSet<>()
        );
        return List.of(user, admin);
    }
}
