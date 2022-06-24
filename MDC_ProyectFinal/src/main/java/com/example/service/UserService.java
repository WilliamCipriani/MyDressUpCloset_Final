package com.example.service;

import java.util.Arrays;
import java.util.List;

import com.example.entities.Report;
import com.example.entities.Users;

import com.example.utils.Constants;

public interface UserService {

	Users insert(Users user);

	Users update(Users user);

	Users getById(Long id);

	void delete(Long id);

	List<Users> findAll();

	Users findByUsername(String username);
	Users findByDni(String dni);
	Users findByEmail(String email);
	
	boolean validatePassword(String password);
	
	public default String getEmailDomain(String email) {
		return email.substring(email.indexOf("@") + 1);
	}

	public default boolean isValidEmail(String email) {
		return Arrays.asList(Constants.VALID_CUSTOMER_DOMAIN_EMAIL,
				Constants.VALID_ADMIN_DOMAIN_EMAIL).contains(getEmailDomain(email));
	}

	public default Users handleUserTypeByEmailDomain(Users user) {
		if (user.getEmail() != null && isValidEmail(user.getEmail())) {
			switch (getEmailDomain(user.getEmail())) {
				case Constants.VALID_ADMIN_DOMAIN_EMAIL:
					user.setType(Constants.ADMIN_TYPE_ID);
					break;
				case Constants.VALID_CUSTOMER_DOMAIN_EMAIL:
					user.setType(Constants.CUSTOMER_TYPE_ID);
					break;
				default:
					break;
			}
		}
		return user;
	}
	
	List<Users> findByRole(int adminNewId);

	public List<Report> userReport();
}
