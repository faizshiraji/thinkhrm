package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Payroll;
import com.hrm.thinkerhouse.repo.PayrollRepo;
import com.hrm.thinkerhouse.services.PayrollService;

@Service
public class PayrollServiceImpl implements PayrollService {

	@Autowired
	public PayrollRepo payrollRepo;
	
	@Override
	public List<Payroll> getPayrolls() {
		return payrollRepo.findAll();
	}

	@Override
	public Payroll getPayroll(int idPayroll) {
		return payrollRepo.findById(idPayroll).get();
	}

	@Override
	public Payroll addPayroll(Payroll payroll) {
		return payrollRepo.save(payroll);
	}

	@Override
	public Payroll updatePayroll(Payroll payroll) {
		return payrollRepo.save(payroll);
	}

	@Override
	public void deletePayroll(int idPayroll) {
		payrollRepo.deleteById(idPayroll);
	}

	@Override
	public long countPayroll() {
		return payrollRepo.count();
	}

}
