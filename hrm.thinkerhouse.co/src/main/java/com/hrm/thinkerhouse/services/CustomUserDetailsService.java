package com.hrm.thinkerhouse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hrm.thinkerhouse.entities.Users;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UsersService usersService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		final Users user = usersService.getUserByUsers(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("This user not found in our database!!!");
		
		}
		
		UserDetails userDetails = User.withUsername(user.getUsername()).password(user.getPassword()).authorities(user.getRole().getRoleName()).build();
		
		return userDetails;
	}

}
