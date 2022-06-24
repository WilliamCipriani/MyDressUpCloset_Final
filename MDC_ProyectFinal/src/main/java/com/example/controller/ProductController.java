package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.ProductoService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductoService productService;
	
	public ProductController(ProductoService productService) {
		this.productService = productService;
	}
	
	
	@GetMapping
	public String home(Model model) {
	        model.addAttribute("products", productService.findAll());
	        return "home";
	}    

	@RequestMapping("/details/{id}")
	public String DetailProducto(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getById(id));
		return "product";
	}



}
