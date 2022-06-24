package com.example.repository;

import java.util.List;

import com.example.entities.PurchaseProduct;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long> {
	List<PurchaseProduct> findByPurchaseId(long id);
}
