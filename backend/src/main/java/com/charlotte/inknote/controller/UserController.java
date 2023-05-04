package com.charlotte.inknote.controller;

import com.charlotte.inknote.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
