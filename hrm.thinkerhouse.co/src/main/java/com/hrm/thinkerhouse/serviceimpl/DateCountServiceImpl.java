package com.hrm.thinkerhouse.serviceimpl;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.services.DateCountService;

@Service
public class DateCountServiceImpl implements DateCountService {

	@Override
    public int getMonthCount() {
        return 12;
    }

    @Override
    public int getWeekCount() {
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        LocalDate endOfYear = LocalDate.of(LocalDate.now().getYear(), 12, 31);
        return endOfYear.get(weekFields.weekOfYear());
    }

    @Override
    public int getDayCount() {
        return LocalDate.now().lengthOfYear();
    }

    @Override
    public int getFridayCount() {
        return getDayOfWeekCount(DayOfWeek.FRIDAY, LocalDate.now().getYear());
    }

    @Override
    public int getSaturdayCount() {
        return getDayOfWeekCount(DayOfWeek.SATURDAY, LocalDate.now().getYear());
    }

    @Override
    public int getDayCountForMonth(int year, int month) {
        return LocalDate.of(year, month, 1).lengthOfMonth();
    }

    @Override
    public int getFridayCountForMonth(int year, int month) {
        return getDayOfWeekCountForMonth(DayOfWeek.FRIDAY, year, month);
    }

    @Override
    public int getSaturdayCountForMonth(int year, int month) {
        return getDayOfWeekCountForMonth(DayOfWeek.SATURDAY, year, month);
    }

    private int getDayOfWeekCount(DayOfWeek dayOfWeek, int year) {
        int count = 0;
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        while (!start.isAfter(end)) {
            if (start.getDayOfWeek() == dayOfWeek) {
                count++;
            }
            start = start.plusDays(1);
        }
        return count;
    }

    private int getDayOfWeekCountForMonth(DayOfWeek dayOfWeek, int year, int month) {
        int count = 0;
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        while (!start.isAfter(end)) {
            if (start.getDayOfWeek() == dayOfWeek) {
                count++;
            }
            start = start.plusDays(1);
        }
        return count;
    }

}
