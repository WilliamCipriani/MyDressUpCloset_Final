package com.example.controller;

import java.text.ParseException;

import javax.servlet.http.HttpSession;

import com.example.entities.DateSelected;
import com.example.entities.Product;
import com.example.entities.Users;
import com.example.service.ProductoService;
import com.example.service.PurchaseService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdmiHomeController {

	// Fechas default
	private int monthDefault = 6;
	private int yearDefault = 2022;

	@Autowired
	private UserService userService;
	@Autowired
	private ProductoService productService;
	@Autowired
	private PurchaseService purchaseService;

	@GetMapping
	public String home(Model model, HttpSession sesion) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		sesion.setAttribute("user", user);
		model.addAttribute("products", productService.findAll());
		model.addAttribute("productBusqueda", new Product());
		return "admiHome";
	}

	@GetMapping("/addproduct")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}

	@GetMapping("/deleteproduct/{id}")
	public String DeleteProducto(@PathVariable("id") Long id, Model model) {
		try {
			if (productService.getById(id) != null) {
				model.addAttribute("delete", productService.delete(id));
			} else
				throw new Exception("Elemento no existe");
		} catch (Exception e) {
			model.addAttribute("delete", "Error al eliminar el producto id:" + id.toString());
		}
		return "delete";
	}

	@GetMapping("/addadmin")
	public String addAdmin(Model model) {
		model.addAttribute("admin", new Users());
		return "addAdmin";
	}

	@PostMapping("/filterProductsByCategory")
	public String filterProductsByCategory(@ModelAttribute("productBusqueda") Product filter, Model model,
			HttpSession sesion) {

		model.addAttribute("products", productService.findByCategory(filter.getName()));
		model.addAttribute("productBusqueda", filter);
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = userService.findByUsername(userDetails.getUsername());
		sesion.setAttribute("user", user);
		System.out.println(filter.getName());
		System.out.println("adasdasdae2112sfar123esda");
		return "admiHome";

	}

	@PostMapping("/resetFilter")
	public String resetFilter(Model model, HttpSession sesion) {
		sesion.setAttribute("filterListOfProductsBy", "seleccionar");
		return "redirect:/admin";
	}

	@GetMapping("/listAdmins")
	public String listAdmins(Model model) {
		model.addAttribute("admins", userService.findByRole(1));
		return "listAdmin";
	}

	@GetMapping("/reports")
	public String reports(Model model) throws ParseException {
		model.addAttribute("productReports", productService.productReport());
		model.addAttribute("userReports", userService.userReport());
		model.addAttribute("dateSelected", new DateSelected(monthDefault, yearDefault));
		try {
			model.addAttribute("purchaseReport", purchaseService.purchaseReport(getDate(monthDefault, yearDefault),
					getLastDate(monthDefault, yearDefault)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "reports";
	}

	@PostMapping("/reports/search")
	public String reportSearch(@ModelAttribute("dateSelected") DateSelected dateSelected, Model model,
			HttpSession sesion) throws ParseException {
		model.addAttribute("productReports", productService.productReport());
		model.addAttribute("userReports", userService.userReport());
		model.addAttribute("purchaseReport",
				purchaseService.purchaseReport(getDate(dateSelected.getMonth(), dateSelected.getYear()),
						getLastDate(dateSelected.getMonth(), dateSelected.getYear())));
		return "reports";
	}

	private String getDate(int month, int year) {
		String monthString = "";
		String yearString = String.valueOf(year);
		if (month < 10) {
			monthString = "0" + String.valueOf(month);
		} else
			monthString = String.valueOf(month);

		return yearString + "-" + monthString + "-01";
	}

	private String getLastDate(int month, int year) {
		int monthAux = month + 1;
		int yearAux = year;
		if (monthAux == 13) {
			monthAux = 1;
			yearAux += 1;
		}
		return getDate(monthAux, yearAux);
	}

}