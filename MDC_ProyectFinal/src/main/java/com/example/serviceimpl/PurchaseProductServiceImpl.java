package com.example.serviceimpl;

import java.util.List;

import com.example.entities.PurchaseProduct;
import com.example.repository.PurchaseProductRepository;
import com.example.service.PurchaseProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseProductServiceImpl implements PurchaseProductService {
	@Autowired
	private PurchaseProductRepository purchaseRepository;

	@Override
	public void insert(PurchaseProduct purchaseProduct) throws Exception {
		purchaseRepository.save(purchaseProduct);
		
	}

	@Override
	public List<PurchaseProduct> findByPurchaseId(long id) throws Exception {
		return purchaseRepository.findByPurchaseId(id);
	}


	
	
}
