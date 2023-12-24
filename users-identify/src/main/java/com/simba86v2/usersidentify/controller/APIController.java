package com.simba86v2.usersidentify.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api")
public class APIController {
    @GetMapping
    public String home() {
        System.out.println("hello callback!!!");
        return "helo";
    }
}