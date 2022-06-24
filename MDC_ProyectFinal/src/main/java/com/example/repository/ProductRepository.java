package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entities.Product;
import com.example.entities.Report;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findByNameContainingIgnoreCase(String name);

	@Query("Select p FROM Product p WHERE p.name LIKE :name")
	public List<Product> findByNameContaining(@Param("name") String name);

	@Query("Select p FROM Product p WHERE p.category LIKE :category")
	public List<Product> findByCategoryContaining(@Param("category") String category);

	@Query("SELECT new com.example.entities.Report(pr.id as id, pr.name as name, sum(p.quantity) as quantity) FROM PurchaseProduct p INNER JOIN Product pr ON pr.id = p.product.id group by pr.id order by sum(p.quantity) desc")
	public List<Report> productReport();
}
