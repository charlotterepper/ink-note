package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.UserAdminDTO;
import com.charlotte.inknote.mapper.UserAdminDTOMapper;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final UserAdminDTOMapper userAdminDTOMapper;

    public AdminController(UserService userService, UserAdminDTOMapper userAdminDTOMapper) {
        this.userService = userService;
        this.userAdminDTOMapper = userAdminDTOMapper;
    }

    @GetMapping("/users/all")
    public List<UserAdminDTO> allUsers() {
        List<User> users = userService.findAll();
        return userAdminDTOMapper.toUserAdminDTOList(users);
    }
}
