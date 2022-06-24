package com.example.controller;

import java.util.List;

import com.example.entities.PurchaseProduct;
import com.example.service.PurchaseProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/purchaseProduct")
public class PurchaseProductController {
	
	@Autowired
	private PurchaseProductService purchaseProductService;
	
	@RequestMapping("/{idPurchase}")
	public String irMyPurchaseProducts(@PathVariable("idPurchase") Long idPurchase, Model model) throws Exception {
		List<PurchaseProduct> purchaseProducts = purchaseProductService.findByPurchaseId(idPurchase);
		model.addAttribute("purchaseProducts", purchaseProducts);
		Float total = (float) 0;
		for (int i = 0; i < purchaseProducts.size(); i++) {
			total += purchaseProducts.get(i).getSubtotal();
		}
		model.addAttribute("total", total);
		return "purchaseProduct";
	}
}
