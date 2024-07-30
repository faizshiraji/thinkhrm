package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.repo.EmployeeRepo;
import com.hrm.thinkerhouse.services.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	public EmployeeRepo employeeRepo;
	
	@Override
	public List<Employee> getEmployees() {
		return employeeRepo.findAll();
	}

	@Override
	public Employee getEmployee(int idEmployee) {
		return employeeRepo.findById(idEmployee).orElseThrow(() -> new ResourceAccessException("Employee not found with id " + idEmployee));
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return employeeRepo.findByEmail(email);
	}

	@Override
	public Employee getEmployeeByPhone(String phone) {
		return employeeRepo.findByPhone(phone);
	}

	@Override
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}

	@Override
	public void deleteEmployee(int idEmployee) {
		employeeRepo.deleteById(idEmployee);
	}

	@Override
	public long countEmployee() {
		return employeeRepo.count();
	}

	@Override
    public Employee getEmployeeByUserId(String userId) {
        return employeeRepo.findByUserId(userId);
    }

	@Override
	public List<Employee> getAllByShiftIsNull() {
		return employeeRepo.findAllByShiftIsNull();
	}

	@Override
	public List<Employee> getEmployeeByStatus(Integer status) {
		return employeeRepo.findAllByStatus(status);
	}

}
