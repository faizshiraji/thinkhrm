package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Users;
import com.hrm.thinkerhouse.repo.UsersRepo;
import com.hrm.thinkerhouse.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	public UsersRepo usersRepo;
	
	@Override
	public List<Users> getUsers() {
		return usersRepo.findAll();
	}

	@Override
	public Users getUsers(int idUsers) {
		return usersRepo.findById(idUsers).get();
	}

	@Override
	public Users getUserByUsers(String username) {
		return usersRepo.findByUsername(username);
	}

	@Override
	public Users addUsers(Users users) {
		return usersRepo.save(users);
	}

	@Override
	public Users updateUsers(Users users) {
		return usersRepo.save(users);
	}

	@Override
	public void deleteUser(int idUsers) {
		usersRepo.deleteById(idUsers);
	}

	@Override
	public long countUsers() {
		return usersRepo.count();
	}

}
