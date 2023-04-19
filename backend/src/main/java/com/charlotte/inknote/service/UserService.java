package com.charlotte.inknote.service;

import com.charlotte.inknote.dto.UserRegistrationDTO;
import com.charlotte.inknote.dto.UserRegistrationDTOMapper;
import com.charlotte.inknote.model.Role;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRegistrationDTOMapper userFullNameDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserRegistrationDTOMapper userFullNameDTOMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userFullNameDTOMapper = userFullNameDTOMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow();
    }

    public User save(UserRegistrationDTO userRegistrationDTO) {
        User user = userFullNameDTOMapper.toUser(userRegistrationDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        return userRepository.save(user);
    }

    public boolean isTaken(String email) {
        return userRepository.existsUserByEmail(email);
    }
}
