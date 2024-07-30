package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.SalaryStructure;
import com.hrm.thinkerhouse.repo.SalaryStructureRepo;

@Service
public class SalaryStructureServiceImpl implements com.hrm.thinkerhouse.services.SalaryStructureService {

	@Autowired
	public SalaryStructureRepo salaryStructureRepo;
	
	@Override
	public List<SalaryStructure> getSalaryStructures() {
		return salaryStructureRepo.findAll();
	}

	@Override
	public SalaryStructure getSalaryStructur(int idSalaryStructure) {
		return salaryStructureRepo.findById(idSalaryStructure).get();
	}

	@Override
	public SalaryStructure addSalaryStructure(SalaryStructure salaryStructure) {
		return salaryStructureRepo.save(salaryStructure);
	}

	@Override
	public SalaryStructure updateSalaryStructure(SalaryStructure salaryStructure) {
		return salaryStructureRepo.save(salaryStructure);
	}

	@Override
	public void deleteSalaryStructure(int idSalaryStructure) {
		salaryStructureRepo.deleteById(idSalaryStructure);
	}

	@Override
	public long countSalaryStructure() {
		return salaryStructureRepo.count();
	}

	@Override
	public List<SalaryStructure> getSalaryStructureByEmployee(Employee employee) {
		return salaryStructureRepo.findAllByEmployee(employee);
	}

	@Override
	public List<SalaryStructure> getSalaryStructureByStatus(Integer status) {
		return salaryStructureRepo.findAllByStatus(status);
	}

}
