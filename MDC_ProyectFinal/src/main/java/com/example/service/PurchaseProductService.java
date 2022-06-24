package com.example.service;

import java.util.List;

import com.example.entities.PurchaseProduct;

public interface PurchaseProductService {
	void insert(PurchaseProduct purchaseProduct) throws Exception;
	List<PurchaseProduct> findByPurchaseId(long id) throws Exception;

}
