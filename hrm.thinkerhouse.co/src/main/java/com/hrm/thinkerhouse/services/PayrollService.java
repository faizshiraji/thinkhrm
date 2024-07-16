package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Payroll;

public interface PayrollService {

	public List<Payroll> getPayrolls();
	public Payroll getPayroll(int idPayroll);
	public Payroll addPayroll(Payroll payroll);
	public Payroll updatePayroll(Payroll payroll);
	public void deletePayroll(int idPayroll);
	public long countPayroll();
	
}
