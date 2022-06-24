package com.example.service;

import java.util.List;

import com.example.entities.Card;

public interface CardService {
	List<Card> findByUserId(Long userId);

	void insert(Card card) throws Exception;
}
