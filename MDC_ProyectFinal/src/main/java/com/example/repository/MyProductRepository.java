package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entities.MyProduct;

@Repository
public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
	
	List<MyProduct> findByUserId(Long id);
	
	void deleteByUserId(long id);
}
