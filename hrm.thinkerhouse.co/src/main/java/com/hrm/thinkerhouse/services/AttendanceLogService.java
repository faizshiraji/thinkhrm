package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.AttendanceLog;

public interface AttendanceLogService {
	
	public List<AttendanceLog> getAttendanceLogs();
	public AttendanceLog getAttendanceLog(int idAttendanceLog);
	public AttendanceLog addAttendanceLog(AttendanceLog attendanceLog);
	public AttendanceLog updateAttendanceLog(AttendanceLog attendanceLog);
	public void deleteAttendanceLog(int idAttendanceLog);
	public long countAttendanceLogs();
	public void convertLogsIfNotConverted();
}
