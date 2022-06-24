package com.example.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import com.example.entities.Report;
import com.example.entities.Role;
import com.example.entities.Users;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private JavaMailSender sender;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Users insert(Users user) {

		Users objUser = user;
		objUser.setPassword(passwordEncoder.encode(user.getPassword()));
		Role rol = new Role();

		if (user.getType() == 0)
			rol.setAuthority("ROLE_USER");
		else
			rol.setAuthority("ROLE_ADMIN");

		rol = roleRepository.save(rol);
		objUser.setRoles(new ArrayList<Role>());
		objUser.getRoles().add(rol);
		objUser = userRepository.save(objUser);
		sendEmailTool(user.getEmail(), user.getUsername());

		return user;
	}

	@Override
	public Users update(Users user) {
		this.userRepository.save(user);
		return user;
	}

	@Override
	public Users getById(Long id) {
		Users user = this.userRepository.findById(id).get();
		return user;
	}

	@Override
	public void delete(Long id) {
		this.userRepository.deleteById(id);
	}

	@Override
	public List<Users> findAll() {
		return this.userRepository.findAll();
	}

	@Override
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public List<Users> findByRole(int roleId) {
		return userRepository.findByRole(roleId);
	}

	private boolean sendEmailTool(String email, String username) {
		boolean send = false;
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setTo(email);
			helper.setSubject("MDUC: Credenciales de usuario");
			helper.setText("Confirmamos la creación del Usuario: " + username + ". ", false);
			sender.send(message);
			send = true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return send;
	}

	@Override
	public Users findByDni(String dni) {
		// TODO Auto-generated method stub
		return userRepository.findByDni(dni);
	}

	@Override
	public Users findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public boolean validatePassword(String password) {
		int special = 0;
		int mayus = 0;
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) == '!' || password.charAt(i) == '#' || password.charAt(i) == '$'
					|| password.charAt(i) == '%' || password.charAt(i) == '&') {
				special++;
			}
			if (Character.isUpperCase(password.charAt(i))) {
				mayus++;
			}
		}
		if (special > 0 && mayus > 0)
			return true;
		else
			return false;

	}

	@Override
	public List<Report> userReport() {
		// TODO Auto-generated method stub
		List<Report> reports = userRepository.userReport();
		if (reports.size() > 3)
			reports = reports.subList(0, 3);
		return reports;
	}

}
