package com.simba68.authorizationserver.controller;

import org.springframework.web.bind.annotation.RestController;

import com.simba68.authorizationserver.dto.CreateAppUser;
import com.simba68.authorizationserver.dto.MessageDto;
import com.simba68.authorizationserver.service.AppUserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AppUserService appUserService;

    @PostMapping
    public ResponseEntity<MessageDto> createUser(@RequestBody CreateAppUser user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<MessageDto> crea(@RequestBody CreateAppUser user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(appUserService.createUser(user));
    }
}
