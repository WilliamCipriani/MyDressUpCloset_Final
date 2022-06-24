package com.example.serviceimpl;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Product;
import com.example.entities.Report;
import com.example.repository.ProductRepository;
import com.example.service.ProductoService;

@Service
public class ProductServiceImpl implements ProductoService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product insert(Product product) {
		this.productRepository.save(product);
		return product;
	}

	@Override
	public Product update(Product product) {
		this.productRepository.save(product);
		return product;
	}

	@Override
	public Product getById(Long id) {
		Product product = this.productRepository.findById(id).get();
		return product;
	}

	@Override
	public String delete(Long id) {
		try {
			this.productRepository.deleteById(id);
		} catch (Exception e) {
			return e.getMessage();
		}
		return "Eliminado con exito el producto id:" + id;
	}

	@Override
	public List<Product> findAll() {
		return this.productRepository.findAll();
	}

	@Override
	public List<Product> findByName(String name) {
		return this.productRepository.findByNameContainingIgnoreCase(name);
	}

	@Override
	public List<Product> findByCategory(String name) {
		return this.productRepository.findByCategoryContaining(name);
	}

	@Override
	public List<Product> findAllOrderedByPrice(Boolean ascend) {
		List<Product> productList = this.productRepository.findAll();
		productList.sort(new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				if (ascend)
					return o1.getPrice().compareTo(o2.getPrice());
				else
					return o2.getPrice().compareTo(o1.getPrice());
			}
		});
		return productList;
	}

	@Override
	public List<Report> productReport() {
		// TODO Auto-generated method stub
		List<Report> reports = productRepository.productReport();
		if (reports.size() > 3)
			reports = reports.subList(0, 3);
		return reports;
	}

}
