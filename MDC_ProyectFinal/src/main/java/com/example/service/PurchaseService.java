package com.example.service;

import java.text.ParseException;
import java.util.List;

import com.example.entities.Purchase;

public interface PurchaseService {

	Purchase insert(Purchase purchase) throws Exception;

	Purchase findById(long id) throws Exception;

	List<Purchase> findByUserId(long id) throws Exception;

	public Float purchaseReport(String date1, String date2) throws ParseException;
}
