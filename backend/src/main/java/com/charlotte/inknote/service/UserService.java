package com.charlotte.inknote.service;

import com.charlotte.inknote.dto.UserRegistrationDTO;
import com.charlotte.inknote.dto.UserRegistrationDTOMapper;
import com.charlotte.inknote.model.Role;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRegistrationDTOMapper userFullNameDTOMapper;

    public UserService(UserRepository userRepository, UserRegistrationDTOMapper userFullNameDTOMapper) {
        this.userRepository = userRepository;
        this.userFullNameDTOMapper = userFullNameDTOMapper;
    }

    public User findByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow();
    }

    public User save(UserRegistrationDTO userFullNameDTO) {
        User user = userFullNameDTOMapper.toUser(userFullNameDTO);
        user.setRole(Role.USER);
        return userRepository.save(user);
    }
}
