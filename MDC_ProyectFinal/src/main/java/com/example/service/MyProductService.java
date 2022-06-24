package com.example.service;

import java.util.List;

import com.example.entities.MyProduct;

public interface MyProductService {
	
	List<MyProduct> findByUserId(Long userId);

	void insert(MyProduct myproduct) throws Exception;

	void delete(long id) throws Exception;

	MyProduct findById(long id) throws Exception;
	
}
