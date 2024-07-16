package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.SalaryComponents;

public interface SalaryComponentsService {

	public List<SalaryComponents> getSalaryComponents();
	public SalaryComponents getSalaryComponents(int idSalaryComponent);
	public SalaryComponents addSalaryComponents(SalaryComponents salaryComponents);
	public SalaryComponents updateSalaryComponents(SalaryComponents salaryComponents);
	public void deleteSalaryComponents(int idSalaryComponent);
	public long countSalaryComponents();
}
