package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Role;

public interface RoleService {

	public List<Role> getRoles();
	public Role getRole(int idRole);
	public Role addRole(Role role);
	public Role updateRole(Role role);
	public void deleteRole(int idRole);
	public long countRole();
	
	
}
