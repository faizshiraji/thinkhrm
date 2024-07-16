package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Shift;
import com.hrm.thinkerhouse.repo.ShiftRepo;
import com.hrm.thinkerhouse.services.ShiftService;

@Service
public class ShiftServiceImpl implements ShiftService {

	@Autowired
	public ShiftRepo shiftRepo;
	
	@Override
	public List<Shift> getShifts() {
		return shiftRepo.findAll();
	}

	@Override
	public Shift getShift(int idShift) {
		return shiftRepo.findById(idShift).get();
	}

	@Override
	public Shift addShift(Shift shift) {
		return shiftRepo.save(shift);
	}

	@Override
	public Shift updateShift(Shift shift) {
		return shiftRepo.save(shift);
	}

	@Override
	public void deleteShift(int idShift) {
		shiftRepo.deleteById(idShift);
	}

	@Override
	public long countShift() {
		return shiftRepo.count();
	}

}
