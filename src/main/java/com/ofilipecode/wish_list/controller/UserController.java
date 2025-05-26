package com.ofilipecode.wish_list.controller;

import java.util.UUID;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofilipecode.wish_list.dtos.user.UserResponseDTO;
import com.ofilipecode.wish_list.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable String id) {
        
        UUID userId = UUID.fromString(id);
        
        Optional<UserResponseDTO> userResponseDTO = service.getUserById(userId);

        if (userResponseDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(userResponseDTO.get());
    }
}
