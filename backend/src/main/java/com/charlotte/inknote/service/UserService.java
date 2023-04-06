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
    private final NoteDTOMapper noteDTOMapper;

    public UserService(UserRepository userRepository, NoteDTOMapper noteDTOMapper) {
        this.userRepository = userRepository;
        this.noteDTOMapper = noteDTOMapper;
    }

    public User findByEmail(Principal principal) {
        return userRepository.findByEmail(principal.getName()).orElseThrow();
    }
}
