package com.example.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		SecurityContextHolder.getContext().setAuthentication(null);
		sesion.setAttribute("user", null);
		return "login";
	}

	@GetMapping
	public String irLogin() {
		return "redirect:/login";
	}


}
