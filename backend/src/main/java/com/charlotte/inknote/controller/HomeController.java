package com.charlotte.inknote.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    @GetMapping("/")
    public String welcome() {
        return "Welcome to InkNote";
    }
}
