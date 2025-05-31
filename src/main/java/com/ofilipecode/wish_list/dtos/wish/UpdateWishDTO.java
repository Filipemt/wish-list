package com.ofilipecode.wish_list.dtos.wish;

import java.math.BigDecimal;

import com.ofilipecode.wish_list.shared.enums.PriorityEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateWishDTO(
    @NotBlank String title,
    @NotBlank String description,
    @NotBlank String link,
    @NotNull BigDecimal price,
    @NotNull PriorityEnum priority
) {}
