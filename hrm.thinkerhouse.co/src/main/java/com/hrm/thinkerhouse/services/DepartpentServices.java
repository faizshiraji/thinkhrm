package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Department;


public interface DepartpentServices {

	public List<Department> getDepartments();
	public Department getDepartment(int idDepartment);
	public Department addDepartment(Department department);
	public Department updateDepartment(Department department);
	public void deleteDepartment(int idDepartment);
	public long countDepartment();
}