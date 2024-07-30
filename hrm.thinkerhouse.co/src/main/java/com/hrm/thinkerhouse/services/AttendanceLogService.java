package com.hrm.thinkerhouse.services;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrm.thinkerhouse.entities.AttendanceLog;

public interface AttendanceLogService {
	
	public List<AttendanceLog> getAttendanceLogs();
	public AttendanceLog getAttendanceLog(int idAttendanceLog);
	public AttendanceLog addAttendanceLog(AttendanceLog attendanceLog);
	public AttendanceLog updateAttendanceLog(AttendanceLog attendanceLog);
	public void deleteAttendanceLog(int idAttendanceLog);
	public long countAttendanceLogs();
	public void convertLogsIfNotConverted();
	public void generateAttendanceLog();
	public String calculateAttendanceStatus(AttendanceLog attendanceLog, LocalTime shiftStartTime);
	public Page<AttendanceLog> getAttendanceLogs(Pageable pageable);
	public Page<AttendanceLog> getAttendanceLogsByDateRange(Date startDate, Date endDate, Pageable pageable);
}
