	package com.hrm.thinkerhouse.controller;
	
	import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
	import java.util.Comparator;
	import java.util.Date;
import java.util.HashSet;
import java.util.List;
	import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
	import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hrm.thinkerhouse.entities.AttendanceLog;
	import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Devices;
import com.hrm.thinkerhouse.entities.Employee;
	import com.hrm.thinkerhouse.entities.Shift;
	import com.hrm.thinkerhouse.services.AttendanceLogService;
	import com.hrm.thinkerhouse.services.DevicePunchLogService;
import com.hrm.thinkerhouse.services.DeviceService;
import com.hrm.thinkerhouse.services.EmployeeService;

import net.bytebuddy.utility.privilege.GetMethodAction;
	
	@Controller
	public class AttendanceLogController {
	
		@Autowired
		private AttendanceLogService attendanceLogService;
		
		@Autowired
		private DevicePunchLogService devicePunchLogService;
		
		@Autowired
		private EmployeeService employeeService;
		
		@Autowired
		private DeviceService deviceService;
		
		private static final Logger logger = LoggerFactory.getLogger(AttendanceLogController.class);
		
		@GetMapping("/collect_attendancelog")
	    public String collectAttendanceLog(Model model) {
	        // Retrieve the employee by user ID
			
			String msgString = "";
			
			List<Employee> Employees = employeeService.getEmployeeByStatus(1);
			
		    try {
				for(Employee employee : Employees){
					
				    if (!employee.getUserId().isEmpty()) {
	
				        List<DevicePunchLog> devicePunchLogByUserID = devicePunchLogService.getDevicePunchLogByUserID(employee.getUserId());
				        
				        if (!devicePunchLogByUserID.isEmpty() || devicePunchLogByUserID != null) {
				            
				            for (DevicePunchLog devicEmployee : devicePunchLogByUserID) {
				            
				                if (devicEmployee.getLogStatus() == 0) {
				                	Employee employeeByUserId = employeeService.getEmployeeByUserId(devicEmployee.getUserID());
				                    List<DevicePunchLog> logByUserIDs = devicePunchLogService.getDevicePunchLogByUserID(employeeByUserId.getUserId());
										
				                    Map<String, List<DevicePunchLog>> punchesByDate = new TreeMap<>();
									SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
										
				                        for (DevicePunchLog log : logByUserIDs) {
											String datePart = dateFormatter.format(log.getRecordTime());
											punchesByDate.computeIfAbsent(datePart, k -> new ArrayList<>()).add(log);
											DevicePunchLog devicePunchLog = devicePunchLogService
													.getDevicePunchLog(log.getIdDevicePunchLog());
											devicePunchLog.setLogStatus(1);
											devicePunchLogService.updateDevicePunchLog(devicePunchLog);
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
	
				        }
	
				    }
	
				}
			} catch (Exception e) {
				msgString = "We encountered an error:" + e.getMessage();
			}
		    
		    System.out.println(msgString);
		    
		    List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogs();
		    
		    model.addAttribute("attendanceLogs", attendanceLogs);
		    model.addAttribute("msgString", msgString);
		    
			return "admin/attendancelog";
	    }
		
		@GetMapping("/all_attendancelog")
	    public String allAttendanceLog(Model model,
	                                   @RequestParam(defaultValue = "0") int page,
	                                   @RequestParam(defaultValue = "10") int size,
	                                   @RequestParam(required = false) String fromDate,
	                                   @RequestParam(required = false) String toDate) {
	        Pageable pageable = PageRequest.of(page, size);
	        Page<AttendanceLog> attendanceLogsPage;

	        SimpleDateFormat inputDateFormat = new SimpleDateFormat("MM/dd/yyyy");
	        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	        try {
	            if (fromDate != null && toDate != null && !fromDate.isEmpty() && !toDate.isEmpty()) {
	                Date startDate = inputDateFormat.parse(fromDate);
	                Date endDate = inputDateFormat.parse(toDate);
	                String formattedStartDate = outputDateFormat.format(startDate);
	                String formattedEndDate = outputDateFormat.format(endDate);

	                Date formattedStart = outputDateFormat.parse(formattedStartDate);
	                Date formattedEnd = outputDateFormat.parse(formattedEndDate);

	                attendanceLogsPage = attendanceLogService.getAttendanceLogsByDateRange(formattedStart, formattedEnd, pageable);
	            } else {
	                attendanceLogsPage = attendanceLogService.getAttendanceLogs(pageable);
	            }
	        } catch (ParseException e) {
	            model.addAttribute("error", "Invalid date format. Please use MM/dd/yyyy.");
	            attendanceLogsPage = attendanceLogService.getAttendanceLogs(pageable);
	        }

	        model.addAttribute("attendanceLogsPage", attendanceLogsPage);
	        model.addAttribute("currentPage", page);
	        model.addAttribute("totalPages", attendanceLogsPage.getTotalPages());
	        model.addAttribute("pageSize", size);
	        model.addAttribute("fromDate", fromDate);
	        model.addAttribute("toDate", toDate);
	        return "admin/attendancelog";
	    }

	    @ExceptionHandler(Exception.class)
	    public ModelAndView handleException(Exception ex) {
	        ModelAndView model = new ModelAndView("error");
	        model.addObject("errorMessage", ex.getMessage());
	        return model;
	    }
		
		@GetMapping("/attendance")
	    public String getAttendancePage(Model model) {
	        List<AttendanceLog> attendanceLogs = attendanceLogService.getAttendanceLogs();
	
	        for (AttendanceLog attendanceLog : attendanceLogs) {
	            Shift shift = attendanceLog.getEmployee().getShift();
	            LocalTime shiftStartTime = shift.getStartTime();
	            String status = attendanceLogService.calculateAttendanceStatus(attendanceLog, shiftStartTime);
	            attendanceLog.setAttendStatus(status); // Set the status directly in the entity for demonstration purposes
	        }
	
	        model.addAttribute("attendanceLogs", attendanceLogs);
	        return "admin/attendancelog";
	    }
		
		@GetMapping("/uploaddata")
		public String uploadData() {
			
			
			
			return "admin/upload_data";
			
		}
		

		@PostMapping("/uploadcsv")
	    public String uploadCSV(@RequestParam("csvfile") MultipartFile file, Model model) {
	        String msgString = "";
	        Set<String> existingEntries = new HashSet<>();
	        SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy h:mm a");
	        Devices devices = deviceService.getDevices(8); // Ensure deviceService is autowired and available

	        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	            String line;
	            boolean isFirstLine = true; // Flag to skip the header
	            while ((line = br.readLine()) != null) {
	                if (isFirstLine) {
	                    isFirstLine = false;
	                    continue; // Skip the header line
	                }

	                String[] fields = line.split(",");
	                logger.debug("Processing line: {}", (Object) fields); // Log the fields being processed

	                if (fields.length < 4) {
	                    msgString = "Invalid CSV format";
	                    model.addAttribute("msgString", msgString);
	                    return "admin/upload_data";
	                }
	                
	                String userID = fields[1];
	                String userName = fields[2]; // Capture user name from the CSV
	                String time = fields[3];

	                try {
	                    System.out.println("Time - " + time);
	                    Date recordTime = formatter.parse(time);
	                    System.out.println("Record Time - " + recordTime);
	                    String uniqueKey = userID + recordTime.toString();

	                    if (!existingEntries.contains(uniqueKey)) {
	                        existingEntries.add(uniqueKey);

	                        // Log the action
	                        logger.info("Processing new entry: UserID = {}, UserName = {}, Time = {}", userID, userName, time);

	                        // Create a new DevicePunchLog object and set its properties
	                        DevicePunchLog log = new DevicePunchLog();
	                        log.setUserID(userID);
	                        log.setUserSN(null); // Set userSN to null as per your code
	                        log.setRecordTime(recordTime);
	                        log.setVerifyType(""); // Assuming default value
	                        log.setVerifyState(""); // Assuming default value
	                        log.setLogStatus(0); // Assuming 1 as default status
	                        log.setCreateDate(new Date());
	                        log.setUpdateDate(new Date());
	                        log.setDevices(devices);

	                        // Save the log object to the database
	                        devicePunchLogService.addDevicePunchLog(log);
	                    }
	                } catch (ParseException e) {
	                    logger.error("Error parsing date-time: {}", time, e);
	                    msgString = "Error processing file: " + e.getMessage();
	                    model.addAttribute("msgString", msgString);
	                    return "admin/upload_data";
	                }
	            }
	            msgString = "File uploaded and data saved successfully";
	        } catch (Exception e) {
	            logger.error("Error processing file: ", e);
	            msgString = "Error processing file: " + e.getMessage();
	        }

	        model.addAttribute("msgString", msgString);
	        logger.info("File '{}' processed successfully", file.getOriginalFilename());
	        return "admin/upload_data";
	    }
			
		}
