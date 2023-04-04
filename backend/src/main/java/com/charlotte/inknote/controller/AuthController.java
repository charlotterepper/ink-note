package com.charlotte.inknote.controller;

import com.charlotte.inknote.dto.LoginRequest;
import com.charlotte.inknote.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    private final TokenService tokenService;

    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/token")
    public String token(Authentication authentication) {
        LOG.debug("Token requested for user " + authentication.getName());
        String token = tokenService.generateToken(authentication);
        LOG.debug("Token granted " + token);
        System.out.println("token: " + token);
        return token;
    }
}
