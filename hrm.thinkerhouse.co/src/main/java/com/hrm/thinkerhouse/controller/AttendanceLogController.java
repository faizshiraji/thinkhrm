	package com.hrm.thinkerhouse.controller;
	
	import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.hrm.thinkerhouse.entities.DeviceUserInfo;
import com.hrm.thinkerhouse.entities.Devices;
import com.hrm.thinkerhouse.entities.Employee;
	import com.hrm.thinkerhouse.entities.Shift;
	import com.hrm.thinkerhouse.services.AttendanceLogService;
	import com.hrm.thinkerhouse.services.DevicePunchLogService;
import com.hrm.thinkerhouse.services.DeviceService;
import com.hrm.thinkerhouse.services.DeviceUserInfoService;
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
		
		@Autowired
		private DeviceUserInfoService deviceUserInfoService;
		
		private static final Logger logger = LoggerFactory.getLogger(AttendanceLogController.class);
		
		
		@GetMapping("/collect_attendancelog")
		public String collectAttendanceLog(Model model,
		                                   @RequestParam(defaultValue = "0") int page,
		                                   @RequestParam(defaultValue = "10") int size,
		                                   @RequestParam(required = false) String fromDate,
		                                   @RequestParam(required = false) String toDate) {
		    String msgString = "";
		    List<Employee> employees = employeeService.getEmployeeByStatus(1);

		    try {
		        for (Employee employee : employees) {
		        	System.out.println(employee.getFirstName());
		            if (employee.getUserId() != null && !employee.getUserId().isEmpty()) {
		                List<DevicePunchLog> devicePunchLogByUserID = devicePunchLogService.getDevicePunchLogByUserID(employee.getUserId());

		                if (devicePunchLogByUserID != null && !devicePunchLogByUserID.isEmpty()) {
		                    for (DevicePunchLog deviceEmployee : devicePunchLogByUserID) {
		                        if (deviceEmployee.getLogStatus() == 0) {
		                            Employee employeeByUserId = employeeService.getEmployeeByUserId(deviceEmployee.getUserID());

		                            if (employeeByUserId != null) {
		                                List<DevicePunchLog> logByUserIDs = devicePunchLogService.getDevicePunchLogByUserID(employeeByUserId.getUserId());

		                                if (logByUserIDs != null) {
		                                    Map<String, List<DevicePunchLog>> punchesByDate = new TreeMap<>();
		                                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

		                                    for (DevicePunchLog log : logByUserIDs) {
		                                        String datePart = dateFormatter.format(log.getRecordTime());
		                                        punchesByDate.computeIfAbsent(datePart, k -> new ArrayList<>()).add(log);
		                                        DevicePunchLog devicePunchLog = devicePunchLogService.getDevicePunchLog(log.getIdDevicePunchLog());

		                                        if (devicePunchLog != null) {
		                                            devicePunchLog.setLogStatus(1);
		                                            devicePunchLogService.updateDevicePunchLog(devicePunchLog);
		                                        }
		                                    }

		                                    List<AttendanceLog> attendanceLogs = new ArrayList<>();

		                                    for (Map.Entry<String, List<DevicePunchLog>> entry : punchesByDate.entrySet()) {
		                                        List<DevicePunchLog> logs = entry.getValue();

		                                        if (!logs.isEmpty()) {
		                                            logs.sort(Comparator.comparing(DevicePunchLog::getRecordTime));

		                                            Date firstPunch = logs.get(0).getRecordTime();
		                                            Date lastPunch = logs.size() > 1 ? logs.get(logs.size() - 1).getRecordTime() : null;

		                                            AttendanceLog attendanceLog = new AttendanceLog();
		                                            attendanceLog.setInTime(firstPunch);
		                                            attendanceLog.setOutTime(lastPunch);
		                                            attendanceLog.setEmployee(employeeByUserId);
		                                            attendanceLog.setAttendStatus("Present");
		                                            attendanceLog.setNote("");
		                                            attendanceLog.setStatus(1);
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
		                }
		            }
		        }
		    } catch (Exception e) {
		        msgString = "We encountered an error: " + e.getMessage();
		        logger.error("Error collecting attendance logs", e);
		    }

		    System.out.println(msgString);

		    Page<AttendanceLog> attendanceLogsPage = attendanceLogService.getAttendanceLogs(PageRequest.of(page, size));
		    model.addAttribute("attendanceLogs", attendanceLogsPage.getContent());
		    model.addAttribute("currentPage", attendanceLogsPage.getNumber());
		    model.addAttribute("totalPages", attendanceLogsPage.getTotalPages());
		    model.addAttribute("pageSize", attendanceLogsPage.getSize());
		    model.addAttribute("fromDate", fromDate);
		    model.addAttribute("toDate", toDate);
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

		    model.addAttribute("attendanceLogs", attendanceLogsPage.getContent());
		    model.addAttribute("currentPage", attendanceLogsPage.getNumber());
		    model.addAttribute("totalPages", attendanceLogsPage.getTotalPages());
		    model.addAttribute("pageSize", attendanceLogsPage.getSize());
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

	        int processedCount = 0;
	        int errorCount = 0;
	        int lineNumber = 0;

	        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
	            String line;
	            boolean isFirstLine = true; // Flag to skip the header

	            while ((line = br.readLine()) != null) {
	                lineNumber++;
	                if (isFirstLine) {
	                    isFirstLine = false;
	                    continue; // Skip the header line
	                }

	                String[] fields = line.split(",");
	                logger.debug("Processing line {}: {}", lineNumber, Arrays.toString(fields)); // Log the fields being processed

	                if (fields.length < 4) {
	                    logger.warn("Invalid CSV format at line {}: {}", lineNumber, Arrays.toString(fields));
	                    errorCount++;
	                    continue; // Skip this row and continue processing
	                }

	                String userID = fields[1];
	                String userName = fields[2]; // Capture user name from the CSV
	                String time = fields[3];

	                try {
	                    Date recordTime = formatter.parse(time);
	                    String uniqueKey = userID + recordTime.toString();
	                    logger.debug("Generated unique key at line {}: {}", lineNumber, uniqueKey);

	                    if (!existingEntries.contains(uniqueKey)) {
	                        existingEntries.add(uniqueKey);

	                        // Log the action
	                        logger.info("Processing new entry at line {}: UserID = {}, UserName = {}, Time = {}", lineNumber, userID, userName, time);

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

	                        // Check and save DeviceUserInfo if not exists
	                        DeviceUserInfo existingUserInfo = deviceUserInfoService.getDeviceUserInfoByUserId(userID);
	                        if (existingUserInfo == null) {
	                            DeviceUserInfo newUserInfo = new DeviceUserInfo();
	                            newUserInfo.setUserid(userID);
	                            newUserInfo.setName(userName);
	                            newUserInfo.setDevices(devices);
	                            newUserInfo.setCreateDate(new Date());
	                            newUserInfo.setUpdateDate(new Date());
	                            newUserInfo.setEnabled(true); // Assuming default values for other fields
	                            newUserInfo.setStatus(1);
	                            // Set other fields as needed

	                            deviceUserInfoService.addDeviceUserInfo(newUserInfo);
	                        }
	                        processedCount++;
	                    } else {
	                        logger.info("Duplicate entry found at line {}: UserID = {}, Time = {}", lineNumber, userID, time);
	                    }
	                } catch (ParseException e) {
	                    logger.error("Error parsing date-time at line {}: {}", lineNumber, time, e);
	                    errorCount++;
	                } catch (Exception e) {
	                    logger.error("Unexpected error processing record at line {}: {}", lineNumber, Arrays.toString(fields), e);
	                    errorCount++;
	                }
	            }
	            msgString = "File uploaded and data saved successfully. Processed records: " + processedCount + ", Errors: " + errorCount;
	        } catch (Exception e) {
	            logger.error("Error processing file: ", e);
	            msgString = "Error processing file: " + e.getMessage();
	        }

	        model.addAttribute("msgString", msgString);
	        logger.info("File '{}' processed successfully. Processed records: {}, Errors: {}", file.getOriginalFilename(), processedCount, errorCount);
	        return "admin/loaddata";
	    }
			
		}
