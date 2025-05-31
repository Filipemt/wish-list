package com.ofilipecode.wish_list.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ofilipecode.wish_list.dtos.wish.CreateWishDTO;
import com.ofilipecode.wish_list.dtos.wish.UpdateWishDTO;
import com.ofilipecode.wish_list.dtos.wish.WishResponseDTO;
import com.ofilipecode.wish_list.model.User;
import com.ofilipecode.wish_list.model.Wish;
import com.ofilipecode.wish_list.repository.UserRepository;
import com.ofilipecode.wish_list.repository.WishRepository;
import com.ofilipecode.wish_list.shared.exceptions.UserNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishService {

    private final WishRepository repository;
    private final UserRepository userRepository;

    public WishResponseDTO createWish(UUID userId, CreateWishDTO dto) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));

        Wish wish = new Wish();
        wish.setTitle(dto.title());
        wish.setDescription(dto.description());
        wish.setLink(dto.link());
        wish.setPrice(dto.price());
        wish.setPriority(dto.priority());
        wish.setUser(user);

        Wish savedWish = repository.save(wish);

        return new WishResponseDTO(
            savedWish.getId(),
            savedWish.getTitle(),
            savedWish.getDescription(),
            savedWish.getLink(),
            savedWish.getPrice(),
            savedWish.getPriority(),
            savedWish.getUser().getId()
        );
    }

    public void updateWish(UUID id, UpdateWishDTO dto) {

        // Pegar o Wish pelo ID passado na URL 
        // Converter a String Id em UUID utilizando fromString
        // Transformar o DTO em entidade
        // Salvar a entidade no banco de dados 

        Wish wish = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Wish not found"));

            if (dto.title() != null && !dto.title().isBlank()) {
                wish.setTitle(dto.title());
            }
            if (dto.description() != null && !dto.description().isBlank()) {
                wish.setDescription(dto.description());
            }
            if (dto.link() != null && !dto.link().isBlank()) {
                wish.setLink(dto.link());
            }
            if (dto.price() != null) {
                wish.setPrice(dto.price());
            }
            if (dto.priority() != null) {
                wish.setPriority(dto.priority());
            }

            repository.save(wish);
    }

    public Optional<WishResponseDTO> getWishById(UUID id) {
        return repository.findById(id)
            .map(wish -> new WishResponseDTO(wish.getId(), wish.getTitle(), wish.getDescription(), wish.getLink(), wish.getPrice(), wish.getPriority(), wish.getUser().getId()));
    }

    public void deleteById(UUID id) {
        repository.findById(id).ifPresent(wish -> repository.delete(wish));
    }
}
