package com.charlotte.inknote.mapper;

import com.charlotte.inknote.dto.UserDTO;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserDTOMapper {
    private final UserService userService;

    public UserDTOMapper(UserService userService) {
        this.userService = userService;
    }

    public User toUser(UserDTO userDTO) {
        return userService.findByEmail(userDTO.getEmail());
    }

    public UserDTO toUserDTO(User user) {
        return new UserDTO(user.getEmail());
    }
}
