package com.ofilipecode.wish_list.controller;

import java.util.Optional;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ofilipecode.wish_list.dtos.wish.CreateWishDTO;
import com.ofilipecode.wish_list.dtos.wish.UpdateWishDTO;
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

    @PutMapping("{id}")
    public ResponseEntity<UpdateWishDTO> updateWish(@PathVariable UUID id, @RequestBody @Valid UpdateWishDTO dto) {

        service.updateWish(id, dto);

        return ResponseEntity.noContent().build();
        
    } 

    @GetMapping("{id}")
    public ResponseEntity<WishResponseDTO> getWishById(@PathVariable String id) {
        
        UUID wishId = UUID.fromString(id);

        Optional<WishResponseDTO> wishResponseDTO = service.getWishById(wishId);

        if (wishResponseDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(wishResponseDTO.get());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleleWishById(@PathVariable String id) {
        UUID wishId = UUID.fromString(id);

        if (wishId == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.deleteById(wishId);
            return ResponseEntity.noContent().build();
        }

    }

    
    
}
