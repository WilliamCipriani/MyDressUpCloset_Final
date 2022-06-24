package com.example.controller;

import java.util.List;

import com.example.entities.Card;
import com.example.entities.Users;
import com.example.service.CardService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;

	@Autowired
	private UserService userService;

	@RequestMapping
	public String irCard(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userService.findByUsername(username);
		List<Card> cards = cardService.findByUserId(user.getId());
		model.addAttribute("cards", cards);
		model.addAttribute("card", new Card());

		return "card";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Card card, BindingResult binRes, Model model) throws Exception {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users user = userService.findByUsername(username);
			if (binRes.hasErrors()) {
				return "redirect:/card";
			} else {
				card.setUser(user);
				cardService.insert(card);
				return "redirect:/card";
			}

		} catch (Exception e) {
			return "redirect:/card";
		}
	}
}
