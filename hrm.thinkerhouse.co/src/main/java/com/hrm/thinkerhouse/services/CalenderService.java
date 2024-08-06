package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Calender;

public interface CalenderService {

	public List<Calender> getCalenders();
	public Calender getCalender(int idCalender);
	public Calender addCalender(Calender calender);
	public Calender updateCalender(Calender calender);
	public void deleteCalender(int idCalender);
	
	
}
