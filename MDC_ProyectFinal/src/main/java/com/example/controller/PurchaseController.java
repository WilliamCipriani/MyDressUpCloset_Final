package com.example.controller;

import java.util.Date;
import java.util.List;

import com.example.entities.MyProduct;
import com.example.entities.Purchase;
import com.example.entities.PurchaseProduct;
import com.example.entities.Users;
import com.example.service.MyProductService;
import com.example.service.PurchaseProductService;
import com.example.service.PurchaseService;
import com.example.service.UserService;
import com.example.serviceimpl.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

	@Autowired
	private MyProductService myProductService;

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchaseProductService purchaseProductService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private MailService mailService;

	@RequestMapping
	public String irPurchase(Model model) throws Exception {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println(username);
		Users user = userService.findByUsername(username);
		List<Purchase> purchases = purchaseService.findByUserId(user.getId());
		model.addAttribute("purchases", purchases);
		Float total = (float) 0;
		for (int i = 0; i < purchases.size(); i++) {
			total += purchases.get(i).getAmount();
		}
		model.addAttribute("total", total);
		return "purchase";
	}

	@RequestMapping("/buy")
	public String buy(@ModelAttribute Purchase purchase, BindingResult binRes, Model model) throws Exception {
		try {
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			Users user = userService.findByUsername(username);
			List<MyProduct> listMyProducts = myProductService.findByUserId(user.getId());

			if (listMyProducts.size() > 0) {
				purchase.setDate(new Date());
				purchase.setUser(user);
				Float total = (float) 0.0;
				for (int i = 0; i < listMyProducts.size(); i++) {
					total += listMyProducts.get(i).getProduct().getPrice();
				}
				purchase.setAmount(total);
				Purchase purchaseSave = purchaseService.insert(purchase);
				mailService.sendEmailTool(user.getEmail(), "Compra MDUC", "Se acaba de realizar una compra por el valor de: " + total);
				PurchaseProduct purchaseProduct = new PurchaseProduct();
				
				for (int i = 0; i < listMyProducts.size(); i++) {
					purchaseProduct = new PurchaseProduct();
					purchaseProduct.setProduct(listMyProducts.get(i).getProduct());
					purchaseProduct.setPurchase(purchaseSave);
					purchaseProduct.setQuantity(listMyProducts.get(i).getQuantity());
					purchaseProductService.insert(purchaseProduct);
					myProductService.delete(listMyProducts.get(i).getIdMyProduct());
				}
				return "redirect:/purchase";
			}
			return "redirect:/myProducts";

		} catch (Exception e) {
			return "redirect:/myProducts";
		}
	}
}
