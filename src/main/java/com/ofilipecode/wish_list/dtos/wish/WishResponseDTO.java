package com.ofilipecode.wish_list.dtos.wish;

import java.math.BigDecimal;
import java.util.UUID;

import com.ofilipecode.wish_list.shared.enums.PriorityEnum;

public record WishResponseDTO(

    UUID id,
    String title,
    String description, 
    String link, 
    BigDecimal price, 
    PriorityEnum priority,
    UUID userID
    ) {
    
}
