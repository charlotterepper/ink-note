package com.charlotte.inknote.dto;

import com.charlotte.inknote.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserAdminDTOMapper {

    public UserAdminDTO toUserAdminDTO(User user) {
        return new UserAdminDTO(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    public List<UserAdminDTO> toUserAdminDTOList(List<User> users) {
        return users.stream()
                .map(this::toUserAdminDTO)
                .collect(Collectors.toList());
    }
}
