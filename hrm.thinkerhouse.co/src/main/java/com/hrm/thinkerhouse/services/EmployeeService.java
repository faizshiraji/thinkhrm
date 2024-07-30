package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Employee;

public interface EmployeeService {
	
	public List<Employee> getEmployees();
	public Employee getEmployee(int idEmployee);
	public Employee getEmployeeByEmail(String email);
	public Employee getEmployeeByPhone(String phone);
	public Employee getEmployeeByUserId(String userId);
	public List<Employee> getEmployeeByStatus(Integer status);
	public Employee addEmployee(Employee employee);
	public Employee updateEmployee(Employee employee);
	public List<Employee> getAllByShiftIsNull();
	public void deleteEmployee(int idEmployee);
	public long countEmployee();
}
