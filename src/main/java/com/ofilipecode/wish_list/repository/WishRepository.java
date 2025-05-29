package com.ofilipecode.wish_list.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ofilipecode.wish_list.model.Wish;

public interface WishRepository extends JpaRepository<Wish, UUID> {
}
