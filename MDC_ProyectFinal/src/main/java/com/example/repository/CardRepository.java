package com.example.repository;

import java.util.List;

import com.example.entities.Card;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
	List<Card> findByUserId(Long id);
}
