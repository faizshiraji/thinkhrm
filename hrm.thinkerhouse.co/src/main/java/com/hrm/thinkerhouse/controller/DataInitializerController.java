package com.hrm.thinkerhouse.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrm.thinkerhouse.entities.Role;
import com.hrm.thinkerhouse.entities.Users;
import com.hrm.thinkerhouse.services.UsersService;

@Controller
public class DataInitializerController {

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/datainit")
	public String dataInitializer(Model model) {
		
		Users users = new Users();
		users.setUsername("admin");
		users.setPassword(passwordEncoder.encode("password"));
		users.setCreateDate(new Date());
		users.setUpdateDate(new Date());
		Role role = new Role();
		role.setRoleName("ADMIN");
		users.setRole(role);
		usersService.addUsers(users);
		
		String msgString = "Admin User Added!!";
		model.addAttribute("msgString", msgString);
		return "home";
		
	}
	
}
