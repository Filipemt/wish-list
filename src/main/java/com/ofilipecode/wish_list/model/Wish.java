package com.ofilipecode.wish_list.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import com.ofilipecode.wish_list.shared.enums.PriorityEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "wish")
@Getter
@Setter
public class Wish {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "wish_id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 5, max = 50)
    private String title;

    @Column(nullable = false)
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @Column(nullable = false)
    @Size(max = 255)
    @Pattern(regexp = "^(http|https)://.*$", message = "Link need starts with http:// ou https://")
    private String link;    

    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "Price needs be greater than zero (0)")
    private BigDecimal price;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriorityEnum priority;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
