package com.charlotte.inknote.runner;

import com.charlotte.inknote.model.Role;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Profile("!test")
public class UserRunner implements CommandLineRunner {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        User user = new User(1L, "John", "Doe", "user@mail.com",
                passwordEncoder.encode("password"), Role.USER);
        User admin = new User(2L, "Jane", "Doe", "admin@mail.com",
                passwordEncoder.encode("adminPass"), Role.ADMIN);
        userRepository.saveAll(List.of(user, admin));
    }
}
