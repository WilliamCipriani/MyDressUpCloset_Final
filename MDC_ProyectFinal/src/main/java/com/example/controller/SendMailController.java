package com.example.controller;

import com.example.serviceimpl.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SendMailController {

	@Autowired
	private MailService mailService;

	@GetMapping("/mail")
	public String index(Model model) {
		return "send_mail_view";
	}

	@PostMapping("/sendMail")
	public String sendMail(@RequestParam("name") String name, @RequestParam("mail") String mail,
			@RequestParam("subject") String subject, @RequestParam("body") String body) {
		mailService.sendEmailTool(mail, subject, body);

		return "/send_mail_view";
	}

}
