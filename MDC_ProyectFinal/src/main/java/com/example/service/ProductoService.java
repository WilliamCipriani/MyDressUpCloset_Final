package com.example.service;

import java.util.List;

import com.example.entities.Product;
import com.example.entities.Report;

public interface ProductoService {

	Product insert(Product product);

	Product update(Product product);

	Product getById(Long id);

	List<Product> findAll();
	
	List<Product> findAllOrderedByPrice(Boolean ascend);

	String delete(Long id);

	List<Product> findByName(String name);
	
	List<Product> findByCategory(String name);
	
	List<Report> productReport();

}
