package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.SalaryStructure;

public interface SalaryStructureService {

	public List<SalaryStructure> getSalaryStructures();
	public SalaryStructure getSalaryStructur(int idSalaryStructure);
	public SalaryStructure addSalaryStructure(SalaryStructure salaryStructure);
	public List<SalaryStructure> getSalaryStructureByEmployee(Employee employee);
	public List<SalaryStructure> getSalaryStructureByStatus(Integer status);
	public SalaryStructure updateSalaryStructure(SalaryStructure salaryStructure);
	public void deleteSalaryStructure(int idSalaryStructure);
	public long countSalaryStructure();
}
