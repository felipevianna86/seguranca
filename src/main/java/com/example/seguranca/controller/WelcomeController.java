package com.example.seguranca.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping
    public String welcome(){
        return "Welcome to my Springboot WEB Api";
    }

    @GetMapping("/users")
    @PreAuthorize("hasAnyRole('managers', 'users')")
    public String users(){
        return "Authorized user";
    }

    @GetMapping("/managers")
    @PreAuthorize("hasAnyRole('managers')")
    public String managers(){
        return "Authorized manager";
    }
}
