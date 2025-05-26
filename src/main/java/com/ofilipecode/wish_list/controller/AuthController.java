package com.ofilipecode.wish_list.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofilipecode.wish_list.dtos.user.UserRegisterDTO;
import com.ofilipecode.wish_list.dtos.user.UserResponseDTO;
import com.ofilipecode.wish_list.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }
}
