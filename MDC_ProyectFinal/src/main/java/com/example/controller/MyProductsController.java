package com.example.controller;

import java.util.List;

import com.example.entities.MyProduct;
import com.example.entities.Product;
import com.example.entities.Purchase;
import com.example.entities.Users;
import com.example.service.CardService;
import com.example.service.MyProductService;
import com.example.service.ProductoService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myProducts")
public class MyProductsController {

	@Autowired
	private ProductoService productService;

	@Autowired
	private MyProductService myProductService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CardService cardService;

	@RequestMapping
	public String irMyProducts(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Users user = userService.findByUsername(username);
		List<MyProduct> myProducts = myProductService.findByUserId(user.getId());
		model.addAttribute("myProducts", myProducts);
		model.addAttribute("purchase", new Purchase());
		model.addAttribute("cards", cardService.findByUserId(user.getId()));
		Float total = (float) 0;
		for (int i = 0; i < myProducts.size(); i++) {
			total += myProducts.get(i).getSubtotal();
		}
		model.addAttribute("total", total);
		return "myProducts";
	}

	@RequestMapping("/add/{id}")
	public String addProduct(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			Product product = productService.getById(id);
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users user = userService.findByUsername(username);

			List<MyProduct> myProducts = myProductService.findByUserId(user.getId());
			boolean inMyProducts = false;
			for (int i = 0; i < myProducts.size(); i++) {
				if (myProducts.get(i).getProduct().getId() == id) {
					myProducts.get(i).setQuantity(myProducts.get(i).getQuantity() + 1);
					myProductService.insert(myProducts.get(i));
					inMyProducts = true;
				}
			}
			if (inMyProducts == false) {
				MyProduct myProduct = new MyProduct();
				myProduct.setQuantity(1);
				myProduct.setUser(user);
				myProduct.setProduct(product);
				myProductService.insert(myProduct);
			}

			return "redirect:/myProducts";
		} catch (Exception e) {
			return "redirect:/product/details/" + id;
		}
	}

	@RequestMapping("/delete/{id}")
	public String deleteProduct(@PathVariable("id") Long id, Model model) throws Exception {
		try {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users user = userService.findByUsername(username);
			MyProduct myProduct = myProductService.findById(id);

			List<MyProduct> myProducts = myProductService.findByUserId(user.getId());
			boolean inMyProducts = false;
			for (int i = 0; i < myProducts.size(); i++) {
				System.out.println(myProducts.get(i).getQuantity());
				if (myProducts.get(i).getProduct().getId() == myProduct.getProduct().getId()
						&& myProducts.get(i).getQuantity() > 1) {
					myProducts.get(i).setQuantity(myProducts.get(i).getQuantity() - 1);
					myProductService.insert(myProducts.get(i));
					inMyProducts = true;
				}
			}
			if (inMyProducts == false) {
				myProductService.delete(id);
			}

			return "redirect:/myProducts";
		} catch (Exception e) {
			return "redirect:/myProducts";
		}
	}
}
