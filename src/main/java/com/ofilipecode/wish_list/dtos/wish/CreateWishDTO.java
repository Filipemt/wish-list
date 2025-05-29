package com.ofilipecode.wish_list.dtos.wish;

import java.math.BigDecimal;

import com.ofilipecode.wish_list.shared.enums.PriorityEnum;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateWishDTO(

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 50, message = "Title must be between 5 and 50 characters")
    String title, 

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 255, message = "Description must be between 10 and 255 characters")
    String description, 

    @Pattern(
        regexp = "^(http|https)://.*$",
        message = "Link must be a valid URL starting with http or https"
    )
    String link, 

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero")
    BigDecimal price, 

    @NotNull(message = "Priority is required")
    PriorityEnum priority) {
    
}
