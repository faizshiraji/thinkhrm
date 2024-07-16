package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Department;
import com.hrm.thinkerhouse.repo.DepartmentRepo;
import com.hrm.thinkerhouse.services.DepartpentServices;

@Service
public class DepartmentServiceImpl implements DepartpentServices {

	@Autowired
	public DepartmentRepo departmentRepo;
	
	@Override
	public List<Department> getDepartments() {
		return departmentRepo.findAll();
	}

	@Override
	public Department getDepartment(int idDepartment) {
		return departmentRepo.findById(idDepartment).get();
	}

	@Override
	public Department addDepartment(Department department) {
		return departmentRepo.save(department);
	}

	@Override
	public Department updateDepartment(Department department) {
		return departmentRepo.save(department);
	}

	@Override
	public void deleteDepartment(int idDepartment) {
		departmentRepo.deleteById(idDepartment);
	}

	@Override
	public long countDepartment() {
		return departmentRepo.count();
	}

}
