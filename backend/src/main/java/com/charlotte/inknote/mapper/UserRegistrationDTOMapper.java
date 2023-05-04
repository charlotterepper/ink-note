package com.charlotte.inknote.mapper;

import com.charlotte.inknote.dto.UserRegistrationDTO;
import com.charlotte.inknote.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserRegistrationDTOMapper {

    public User toUser(UserRegistrationDTO userFullNameDTO) {
        return new User(
                userFullNameDTO.getFirstName(),
                userFullNameDTO.getLastName(),
                userFullNameDTO.getEmail(),
                userFullNameDTO.getPassword()
        );
    }
}
