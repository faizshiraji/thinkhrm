package com.hrm.thinkerhouse.services;

public interface DateCountService {

	public int getMonthCount();
	public int getWeekCount();
	public int getDayCount();
	public int getFridayCount();
	public int getSaturdayCount();
	public int getDayCountForMonth(int year, int month);
    public int getFridayCountForMonth(int year, int month);
    public int getSaturdayCountForMonth(int year, int month);
}
