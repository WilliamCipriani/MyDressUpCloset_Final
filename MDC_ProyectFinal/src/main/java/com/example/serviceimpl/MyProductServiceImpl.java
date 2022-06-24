package com.example.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entities.MyProduct;
import com.example.repository.MyProductRepository;
import com.example.service.MyProductService;

@Service
public class MyProductServiceImpl implements MyProductService {

	@Autowired
	private MyProductRepository myProductRepository;

	@Override
	public List<MyProduct> findByUserId(Long userId) {
		List<MyProduct> myproducts = myProductRepository.findByUserId(userId);
		return myproducts;
	}
	
	@Override
	public void insert(MyProduct myproduct) throws Exception {
		myProductRepository.save(myproduct);
	}

	@Override
	public void delete(long id) throws Exception {
		myProductRepository.deleteById(id);
	}
	
	@Override
	public MyProduct findById(long id) throws Exception {
		return myProductRepository.findById(id).get();
	}

}
