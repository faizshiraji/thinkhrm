package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Calender;
import com.hrm.thinkerhouse.repo.CalenderRepo;
import com.hrm.thinkerhouse.services.CalenderService;

@Service
public class CalenderServiceImpl implements CalenderService {

	@Autowired
	private CalenderRepo calenderRepo;
	
	
	@Override
    public List<Calender> getCalenders() {
        List<Calender> holidays = calenderRepo.findAll();
        holidays.forEach(holiday -> System.out.println("Holiday: " + holiday.getName() + " on " + holiday.getDate()));
        return holidays;
    }

	@Override
	public Calender getCalender(int idCalender) {
		return calenderRepo.findById(idCalender).get();
	}

	@Override
	public Calender addCalender(Calender calender) {
		return calenderRepo.save(calender);
	}

	@Override
	public Calender updateCalender(Calender calender) {
		return calenderRepo.save(calender);
	}

	@Override
	public void deleteCalender(int idCalender) {
		calenderRepo.deleteById(idCalender);
	}

}
