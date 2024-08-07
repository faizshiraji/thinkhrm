package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {

	Users findByUsername(String username);
	
}
