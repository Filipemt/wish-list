package com.ofilipecode.wish_list.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ofilipecode.wish_list.dtos.user.UserLoginRequestDTO;
import com.ofilipecode.wish_list.dtos.user.UserRegisterDTO;
import com.ofilipecode.wish_list.dtos.user.UserResponseDTO;
import com.ofilipecode.wish_list.model.User;
import com.ofilipecode.wish_list.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDTO register(UserRegisterDTO dto) {

        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new RuntimeException("E-mail já cadastrado.");
        }

        String encryptedPassword = passwordEncoder.encode(dto.password());
        
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(encryptedPassword);

        userRepository.save(user);

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail());
    }

    public String login(UserLoginRequestDTO dto) {

        User user = userRepository.findByEmail(dto.email())
            .orElseThrow(() -> new RuntimeException("E-mail ou senha incorretos!"));

        if (!passwordEncoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("E-mail ou senha incorretos!");
        }

        return "Login com sucesso";
    }
}
