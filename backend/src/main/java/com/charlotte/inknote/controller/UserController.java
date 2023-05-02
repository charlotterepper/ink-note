package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.UserAdminDTO;
import com.charlotte.inknote.dto.UserAdminDTOMapper;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/role/{principalEmail}")
    public String getPrincipalRole(@PathVariable String principalEmail) {
        return userService.getUserRole(principalEmail);
    }
}
