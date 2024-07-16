package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Role;
import com.hrm.thinkerhouse.repo.RoleRepo;
import com.hrm.thinkerhouse.services.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	public RoleRepo roleRepo;
	
	@Override
	public List<Role> getRoles() {
		return roleRepo.findAll();
	}

	@Override
	public Role getRole(int idRole) {
		return roleRepo.findById(idRole).get();
	}

	@Override
	public Role addRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public Role updateRole(Role role) {
		return roleRepo.save(role);
	}

	@Override
	public void deleteRole(int idRole) {
		roleRepo.deleteById(idRole);
	}

	@Override
	public long countRole() {
		return roleRepo.count();
	}

}
