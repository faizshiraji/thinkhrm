package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.SalaryComponents;
import com.hrm.thinkerhouse.repo.SalaryComponentsRepo;
import com.hrm.thinkerhouse.services.SalaryComponentsService;

@Service
public class SalaryComponentsServiceImpl implements SalaryComponentsService {

	@Autowired
	public SalaryComponentsRepo salaryComponentsRepo;
	
	@Override
	public List<SalaryComponents> getSalaryComponents() {
		return salaryComponentsRepo.findAll();
	}

	@Override
	public SalaryComponents getSalaryComponents(int idSalaryComponent) {
		return salaryComponentsRepo.findById(idSalaryComponent).get();
	}

	@Override
	public SalaryComponents addSalaryComponents(SalaryComponents salaryComponents) {
		return salaryComponentsRepo.save(salaryComponents);
	}

	@Override
	public SalaryComponents updateSalaryComponents(SalaryComponents salaryComponents) {
		return salaryComponentsRepo.save(salaryComponents);
	}

	@Override
	public void deleteSalaryComponents(int idSalaryComponent) {
		salaryComponentsRepo.deleteById(idSalaryComponent);
	}

	@Override
	public long countSalaryComponents() {
		return salaryComponentsRepo.count();
	}

}
