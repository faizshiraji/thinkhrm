package com.hrm.thinkerhouse.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.AttendanceLog;
import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.repo.AttendanceLogRepo;
import com.hrm.thinkerhouse.services.AttendanceLogService;
import com.hrm.thinkerhouse.services.DevicePunchLogService;
import com.hrm.thinkerhouse.services.EmployeeService;

@Service
public class AttendanceLogServiceImpl implements AttendanceLogService {

	@Autowired
	private AttendanceLogRepo attendanceLogRepo;

	@Autowired
	private DevicePunchLogService devicePunchLogService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public List<AttendanceLog> getAttendanceLogs() {
		return attendanceLogRepo.findAll();
	}

	@Override
	public AttendanceLog getAttendanceLog(int idAttendanceLog) {
		return attendanceLogRepo.findById(idAttendanceLog).get();
	}

	@Override
	public AttendanceLog addAttendanceLog(AttendanceLog attendanceLog) {
		return attendanceLogRepo.save(attendanceLog);
	}

	@Override
	public AttendanceLog updateAttendanceLog(AttendanceLog attendanceLog) {
		return attendanceLogRepo.save(attendanceLog);
	}

	@Override
	public void deleteAttendanceLog(int idAttendanceLog) {
		attendanceLogRepo.deleteById(idAttendanceLog);
	}

	@Override
	public long countAttendanceLogs() {
		return attendanceLogRepo.count();
	}
	
	@Override
    public Page<AttendanceLog> getAttendanceLogsByDateRange(Date startDate, Date endDate, Pageable pageable) {
        return attendanceLogRepo.findAllByInTimeBetween(startDate, endDate, pageable);
    }
	
	public void convertLogsIfNotConverted() {
	    List<DevicePunchLog> devicePunchLogs = devicePunchLogService.getDevicePUnchLogByStatus(0);
	    
	    // Group logs by userID and date (using java.util.Date)
	    Map<String, Map<Date, List<DevicePunchLog>>> groupedLogs = devicePunchLogs.stream()
	            .collect(Collectors.groupingBy(DevicePunchLog::getUserID,
	                    Collectors.groupingBy(log -> truncateTime(log.getRecordTime()))));

	    for (Map.Entry<String, Map<Date, List<DevicePunchLog>>> userEntry : groupedLogs.entrySet()) {
	        String userId = userEntry.getKey();
	        Map<Date, List<DevicePunchLog>> logsByDate = userEntry.getValue();
	        
	        for (Map.Entry<Date, List<DevicePunchLog>> dateEntry : logsByDate.entrySet()) {
	            Date logDate = dateEntry.getKey();
	            List<DevicePunchLog> logs = dateEntry.getValue();
	            
	            logs.sort(Comparator.comparing(DevicePunchLog::getRecordTime));
	            
	            if (!logs.isEmpty()) {
	                Employee employee = employeeService.getEmployeeByUserId(userId);
	                if (employee != null) {
	                    DevicePunchLog firstLog = logs.get(0);
	                    DevicePunchLog lastLog = logs.get(logs.size() - 1);
	                    
	                    // Check if an AttendanceLog already exists for this date and user
	                    AttendanceLog existingLog = attendanceLogRepo.findByInIdAndOutId(firstLog.getIdDevicePunchLog(), lastLog.getIdDevicePunchLog());
	                    if (existingLog == null) {
	                        AttendanceLog attendanceLog = new AttendanceLog();
	                        attendanceLog.setEmployee(employee);
	                        attendanceLog.setInTime(truncateTime(logDate)); // Use inTime for date without time
	                        attendanceLog.setOutTime(truncateTime(logDate)); // Use outTime for date without time
	                        attendanceLog.setInId(firstLog.getIdDevicePunchLog());
	                        attendanceLog.setOutId(lastLog.getIdDevicePunchLog());
	                        attendanceLog.setStatus(1); // set appropriate status
	                        attendanceLog.setAttendStatus("Present"); // set appropriate attend status
	                        attendanceLog.setNote(""); // add any notes if necessary
	                        
	                        attendanceLogRepo.save(attendanceLog);
	                    }
	                    
	                    // Update the status of the converted logs
	                    for (DevicePunchLog log : logs) {
	                        log.setLogStatus(1); // mark as converted
	                        devicePunchLogService.updateDevicePunchLog(log);
	                    }
	                }
	            }
	        }
	    }
	}

	// Helper method to truncate time part of java.util.Date
	private Date truncateTime(Date date) {
	    Calendar calendar = Calendar.getInstance();
	    calendar.setTime(date);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
	
	
//	--------------------------------------------------
	
  public void generateAttendanceLog() {
      // Retrieve the employee by user ID
		
		List<AttendanceLog> findAll = attendanceLogRepo.findAll();
		if (findAll != null) {
			System.out.println(findAll.size());
			
			for (AttendanceLog attendanceLog : findAll) {
				
				if (attendanceLog != null) {
					System.out.println(attendanceLog.getOutTime());
				} else {
					System.out.println( "This is Null - " + attendanceLog.getIdAttendanceLog());
				}
				
			}
			
		}
  }
  
  public String calculateAttendanceStatus(AttendanceLog attendanceLog, LocalTime shiftStartTime) {
      if (attendanceLog.getInTime() == null) {
          return "Absent";
      }

      LocalTime inTime = LocalTime.ofInstant(attendanceLog.getInTime().toInstant(), ZoneId.systemDefault());
      if (inTime.isAfter(shiftStartTime)) {
          return "Late";
      }

      return "Present";
  }

  @Override
  public Page<AttendanceLog> getAttendanceLogs(Pageable pageable) {
      return attendanceLogRepo.findAll(pageable);
  }

}
