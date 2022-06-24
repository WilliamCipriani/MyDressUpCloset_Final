package com.example.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.example.entities.Product;
import com.example.entities.Users;
import com.example.service.ProductoService;
import com.example.service.UserService;
import com.example.utils.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProductoService productService;
	@Autowired
	private UserService userService;
	
	public UserController(ProductoService productService,UserService userService) {
		this.productService = productService;
		this.userService = userService;
	}

	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("user", new Users());
		return "register";
	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Users user, BindingResult binRes, Model model) throws ParseException {
		try {
			if (binRes.hasErrors()) {
				return "register";
			} else {
				boolean valid = true;
				if (userService.findByDni(user.getDni()) != null) {
					valid = false;
					model.addAttribute("mensaje", "Dni repetido");
				} else if (userService.findByEmail(user.getEmail()) != null) {
					valid = false;
					model.addAttribute("mensaje", "Email repetido");
				} else if (userService.findByUsername(user.getUsername()) != null) {
					valid = false;
					model.addAttribute("mensaje", "Username repetido");
				} else if (userService.validatePassword(user.getPassword()) == false) {
					valid = false;
					model.addAttribute("mensaje",
							"La contraseña debe contener una mayúscula y una caracter especial !#$%&");
				}
				if (valid == false) {
					model.addAttribute("user", user);
					return "register";
				}

				Users userSave = userService.insert(user);
				if (userSave != null) {
					return "redirect:/login";
				} else {
					model.addAttribute("mensaje", "Ocurrió un error");
					model.addAttribute("user", user);
					return "register";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("mensaje", "Ocurrió un error");
			model.addAttribute("user", user);
			return "register";
		}
	}

	@GetMapping("/myProducts")
	public String myProducts(Model model) {
		return "myProducts";
	}

	@PostMapping("/saveNewProduct")
	public String saveNewProduct(Model model, @ModelAttribute("product") Product product, RedirectAttributes attributes,
			HttpSession sesion) {
		this.productService.insert(product);
		attributes.addFlashAttribute("success",
				"Product with name [" + product.getName() + "] was created successfully.");
		return "redirect:/admin";
	}

	@PostMapping("/registerNewAdminFromAdmin")
	public String registerNewAdminFromAdmin(Model model, @ModelAttribute("admin") Users admin,
			RedirectAttributes attributes, HttpSession sesion) {
		try {
			if (this.userService.isValidEmail(admin.getEmail()) && this.userService.getEmailDomain(admin.getEmail())
					.equalsIgnoreCase(Constants.VALID_ADMIN_DOMAIN_EMAIL)) {
				this.userService.insert(this.userService.handleUserTypeByEmailDomain(admin));
			} else {
				attributes.addFlashAttribute("error",
						"You can only provide email domain equals to [" + Constants.VALID_ADMIN_DOMAIN_EMAIL + "].");
			}
		} catch (Exception e) {
			attributes.addFlashAttribute("error",
					"The email [" + admin.getEmail() + "] is already registered. Try with a new one.");
			return "redirect:/admin/addadmin";
		}
		attributes.addFlashAttribute("success", "Admin account [" + admin.getEmail() + "] was created successfully.");
		return "redirect:/admin";
	}
}
