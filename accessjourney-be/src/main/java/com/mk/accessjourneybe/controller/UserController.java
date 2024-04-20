package com.mk.accessjourneybe.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/AccessJourney/user")
public class UserController {

    @PostMapping(value = "demo")
    public String welcome(){
        return "Welcome form secure endpoint";
    }
}
