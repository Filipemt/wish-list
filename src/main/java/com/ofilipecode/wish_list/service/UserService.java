package com.ofilipecode.wish_list.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ofilipecode.wish_list.dtos.user.UserResponseDTO;
import com.ofilipecode.wish_list.dtos.user.UserUpdateDTO;
import com.ofilipecode.wish_list.model.User;
import com.ofilipecode.wish_list.repository.UserRepository;
import com.ofilipecode.wish_list.shared.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void updateUser(UUID id, UserUpdateDTO dto) {
        User user = repository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.email());

        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        repository.save(user);
    }

    public void updatePartialUser(String id, UserUpdateDTO dto) {

        UUID userId = UUID.fromString(id);
    
        User user = repository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + id));

        if (dto.name() != null && !dto.name().isBlank()) {
            user.setName(dto.name());
        }
        if (dto.email() != null && !dto.email().isBlank()) {
            user.setEmail(dto.email());
        }
        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        repository.save(user);
    }

    public Optional<UserResponseDTO> getUserById(UUID id) {
        return repository.findById(id)
            .map(user -> new UserResponseDTO(user.getId(), user.getName(), user.getEmail()));
    }

    public void deleteById(UUID id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
