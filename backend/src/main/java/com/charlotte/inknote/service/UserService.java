package com.charlotte.inknote.service;

import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import org.springframework.stereotype.Service;

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
