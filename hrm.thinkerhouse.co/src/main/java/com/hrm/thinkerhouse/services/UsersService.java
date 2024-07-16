package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Users;

public interface UsersService {

	public List<Users> getUsers();
	public Users getUsers(int idUsers);
	public Users getUserByUsers(String username);
	public Users addUsers(Users users);
	public Users updateUsers(Users users);
	public void deleteUser(int idUsers);
	public long countUsers();
	
}
