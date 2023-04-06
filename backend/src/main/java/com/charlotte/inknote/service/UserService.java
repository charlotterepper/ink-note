package com.charlotte.inknote.service;

import com.charlotte.inknote.dto.NoteDTO;
import com.charlotte.inknote.dto.NoteDTOMapper;
import com.charlotte.inknote.model.Note;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.NoteRepository;
import com.charlotte.inknote.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow();
    }
}
