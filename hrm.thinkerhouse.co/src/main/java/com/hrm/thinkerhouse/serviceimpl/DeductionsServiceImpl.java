package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Deductions;
import com.hrm.thinkerhouse.repo.DeductionsRepo;
import com.hrm.thinkerhouse.services.DeductionsService;

@Service
public class DeductionsServiceImpl implements DeductionsService {

	@Autowired
	public DeductionsRepo deductionsRepo;
	
	@Override
	public List<Deductions> getDeductions() {
		return deductionsRepo.findAll();
	}

	@Override
	public Deductions getDeductions(int idDeductions) {
		return deductionsRepo.findById(idDeductions).get();
	}

	@Override
	public Deductions addDeductions(Deductions deductions) {
		return deductionsRepo.save(deductions);
	}

	@Override
	public Deductions updateDeductions(Deductions deductions) {
		return deductionsRepo.save(deductions);
	}

	@Override
	public void deleteDeductions(int idDeductions) {
		deductionsRepo.deleteById(idDeductions);
	}

	@Override
	public long countDeductions() {
		return deductionsRepo.count();
	}

}
