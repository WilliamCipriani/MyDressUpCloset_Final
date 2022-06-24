package com.example.serviceimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.Purchase;
import com.example.repository.PurchaseRepository;
import com.example.service.PurchaseService;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	private PurchaseRepository purchaseRepository;

	@Override
	public Purchase findById(long id) throws Exception {
		return purchaseRepository.findById(id).get();
	}

	@Override
	public List<Purchase> findByUserId(long id) throws Exception {
		return purchaseRepository.findByUserId(id);
	}

	@Override
	public Purchase insert(Purchase purchase) throws Exception {
		return purchaseRepository.save(purchase);
	}

	@Override
	public Float purchaseReport(String date1, String date2) throws ParseException {
		// TODO Auto-generated method stub
		Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
		Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
		return purchaseRepository.purchaseReport(d1, d2);
	}

}
