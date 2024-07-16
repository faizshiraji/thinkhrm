package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Deductions;

public interface DeductionsService {
	public List<Deductions> getDeductions();
	public Deductions getDeductions(int idDeductions);
	public Deductions addDeductions(Deductions deductions);
	public Deductions updateDeductions(Deductions deductions);
	public void deleteDeductions(int idDeductions);
	public long countDeductions();
}
