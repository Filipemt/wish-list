package com.ofilipecode.wish_list.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofilipecode.wish_list.dtos.wish.CreateWishDTO;
import com.ofilipecode.wish_list.dtos.wish.WishResponseDTO;
import com.ofilipecode.wish_list.service.WishService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishController {
    
    private final WishService service;

    @PostMapping("{id}")
    public ResponseEntity<WishResponseDTO> createWish(@PathVariable UUID id, @RequestBody @Valid CreateWishDTO dto) {

        WishResponseDTO response = service.createWish(id, dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
