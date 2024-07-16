package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.SalaryStructure;

public interface SalaryStructureService {

	public List<SalaryStructure> getSalaryStructures();
	public SalaryStructure getSalaryStructur(int idSalaryStructure);
	public SalaryStructure addSalaryStructure(SalaryStructure salaryStructure);
	public SalaryStructure updateSalaryStructure(SalaryStructure salaryStructure);
	public void deleteSalaryStructure(int idSalaryStructure);
	public long countSalaryStructure();
}
