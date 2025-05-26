package com.ofilipecode.wish_list.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ofilipecode.wish_list.dtos.user.UserUpdateDTO;
import com.ofilipecode.wish_list.model.User;
import com.ofilipecode.wish_list.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public void updateUser(UUID id, UserUpdateDTO dto) {
        User user = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(dto.name());
        user.setEmail(dto.email());

        if (dto.password() != null && !dto.password().isBlank()) {
            user.setPassword(passwordEncoder.encode(dto.password()));
        }

        repository.save(user);
    }

    public Optional<User> findById(UUID id) {
        return repository.findById(id);
    }

    public void deleteById(UUID id) {
        repository.findById(id).ifPresent(user -> repository.delete(user));
    }
}
