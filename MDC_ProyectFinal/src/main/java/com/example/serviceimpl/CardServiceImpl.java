package com.example.serviceimpl;

import java.util.List;

import com.example.entities.Card;
import com.example.repository.CardRepository;
import com.example.service.CardService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardRepository cardRepository;

	@Override
	public List<Card> findByUserId(Long userId) {
		// TODO Auto-generated method stub
		return cardRepository.findByUserId(userId);
	}

	@Override
	public void insert(Card card) throws Exception {
		// TODO Auto-generated method stub
		cardRepository.save(card);
	}

}
