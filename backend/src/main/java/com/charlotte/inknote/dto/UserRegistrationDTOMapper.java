package com.charlotte.inknote.dto;

import com.charlotte.inknote.model.User;
import org.springframework.stereotype.Service;

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

    public UserRegistrationDTO toUserFullNameDTO(User user) {
        return new UserRegistrationDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword()
        );
    }
}
