package com.hrm.thinkerhouse.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrm.thinkerhouse.entities.AttendanceLog;
import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.services.AttendanceLogService;
import com.hrm.thinkerhouse.services.DevicePunchLogService;
import com.hrm.thinkerhouse.services.EmployeeService;

@Controller
public class AttendanceLogController {

	@Autowired
	private AttendanceLogService attendanceLogService;
	
	@Autowired
	private DevicePunchLogService devicePunchLogService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all_attendancelog")
    public String allAttendanceLog(Model model) {
        // Retrieve the employee by user ID
		
		List<Employee> employees = employeeService.getEmployees();
		
		
		for (Employee employee : employees) {
			
			if (!employee.getUserId().isEmpty()) {
				Employee employeeByUserId = employeeService.getEmployeeByUserId(employee.getUserId());
				List<DevicePunchLog> logByUserIDs = devicePunchLogService
						.getDevicePunchLogByUserID(employeeByUserId.getUserId());
				Map<String, List<DevicePunchLog>> punchesByDate = new TreeMap<>();
				SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
				for (DevicePunchLog log : logByUserIDs) {
					String datePart = dateFormatter.format(log.getRecordTime());
					punchesByDate.computeIfAbsent(datePart, k -> new ArrayList<>()).add(log);
				}
				SimpleDateFormat timeFormatter = new SimpleDateFormat("HH:mm:ss");
				List<AttendanceLog> attendanceLogs = new ArrayList<>();
				for (Map.Entry<String, List<DevicePunchLog>> entry : punchesByDate.entrySet()) {
					List<DevicePunchLog> logs = entry.getValue();
					logs.sort(Comparator.comparing(DevicePunchLog::getRecordTime));

					Date firstPunch = logs.get(0).getRecordTime();
					Date lastPunch = logs.size() > 1 ? logs.get(logs.size() - 1).getRecordTime() : null;

					AttendanceLog attendanceLog = new AttendanceLog();
					attendanceLog.setInTime(firstPunch);
					attendanceLog.setOutTime(lastPunch);
					attendanceLog.setEmployee(employeeByUserId);
					attendanceLog.setAttendStatus("Present"); // or any appropriate status
					attendanceLog.setNote(""); // or any appropriate note
					attendanceLog.setStatus(1); // or any appropriate status code
					attendanceLog.setInId(logs.get(0).getIdDevicePunchLog());
					attendanceLog.setOutId(logs.size() > 1 ? logs.get(logs.size() - 1).getIdDevicePunchLog() : 0);

					attendanceLogs.add(attendanceLog);

					attendanceLogService.addAttendanceLog(attendanceLog);
				} 
			}
		}
		
        
		List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogs();
		
        model.addAttribute("attendanceLogs", attendanceLogs);

        return "admin/attendancelog";
    }
	
//	@GetMapping("/all_attendancelog")
//    public String allAttendanceLog(Model model) {
//        
//		
//		
//		try {
//			attendanceLogService.convertLogsIfNotConverted();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//        
//        List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogs();
//        
//        model.addAttribute("attendanceLogs", attendanceLogs);
//        
//        return "admin/attendancelog";
//    }
		
	}
