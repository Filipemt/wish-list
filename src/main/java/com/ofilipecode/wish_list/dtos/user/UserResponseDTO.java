package com.ofilipecode.wish_list.dtos.user;

import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email) {
}
