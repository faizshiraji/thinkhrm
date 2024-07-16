package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Shift;

public interface ShiftService {

	public List<Shift> getShifts();
	public Shift getShift(int idShift);
	public Shift addShift(Shift shift);
	public Shift updateShift(Shift shift);
	public void deleteShift(int idShift);
	public long countShift();
	
}
