package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

import com.example.entities.Product;
import com.example.entities.Users;
import com.example.service.ProductoService;
import com.example.service.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

	@Autowired
	private UserService userService;
	@Autowired
	private ProductoService productService;

	@RequestMapping
	public String irHome(Model model, HttpSession sesion) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		sesion.setAttribute("user", user);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productBusqueda", new Product());
		return "home";
	}

	@RequestMapping("/buscarProductos")
	public String buscarProductos(@ModelAttribute Product productBusqueda, BindingResult binRes, Model model) {
		if (productBusqueda.getName().equals(""))
			model.addAttribute("products", productService.findAll());
		else
			model.addAttribute("products", productService.findByName(productBusqueda.getName()));
		model.addAttribute("productBusqueda", new Product());
		return "home";
	}
	
	@RequestMapping("/ordenarProductos/{asc}")
	public String ordenarProductos(@PathVariable("asc") String ordenadoAsc, Model model) {
		if(ordenadoAsc.contains("asc"))
			model.addAttribute("products", productService.findAllOrderedByPrice(true));
		else
			model.addAttribute("products", productService.findAllOrderedByPrice(false));
		model.addAttribute("productBusqueda", new Product());
		return "home";
	}

}
