package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.LoginRequest;
import com.charlotte.inknote.dto.UserRegistrationDTO;
import com.charlotte.inknote.model.User;
import com.charlotte.inknote.service.TokenService;
import com.charlotte.inknote.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager, UserService userService) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/token")
    public String token(@RequestBody LoginRequest userLogin) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLogin.email(), userLogin.password()));
        return tokenService.generateToken(authentication);
    }

    @PostMapping("/registration")
    public void registration(@RequestBody UserRegistrationDTO userRegistrationDTO) {
        User savedUser = userService.save(userRegistrationDTO);
        System.out.println("savedUser from registration: {" + savedUser.getFirstName() + ", " + savedUser.getLastName() +
                ", " + savedUser.getEmail() + ", " + savedUser.getPassword() + "}");

    }

    @GetMapping("/email-taken/{email}")
    public boolean emailTaken(@PathVariable String email) {
        return userService.isTaken(email);
    }
}
