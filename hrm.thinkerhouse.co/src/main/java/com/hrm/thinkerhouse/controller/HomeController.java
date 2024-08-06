package com.hrm.thinkerhouse.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrm.thinkerhouse.entities.Branch;
import com.hrm.thinkerhouse.entities.Department;
import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.DeviceUserInfo;
import com.hrm.thinkerhouse.entities.Devices;
import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.Team;
import com.hrm.thinkerhouse.services.BranchService;
import com.hrm.thinkerhouse.services.DepartpentServices;
import com.hrm.thinkerhouse.services.DevicePunchLogService;
import com.hrm.thinkerhouse.services.DeviceService;
import com.hrm.thinkerhouse.services.DeviceUserInfoService;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.TeamService;
import com.hrm.thinkerhouse.zkt.Enum.CommandReplyCodeEnum;
import com.hrm.thinkerhouse.zkt.command.AttendanceRecord;
import com.hrm.thinkerhouse.zkt.command.UserInfo;
import com.hrm.thinkerhouse.zkt.command.ZKCommandReply;
import com.hrm.thinkerhouse.zkt.exceptions.DeviceNotConnectException;
import com.hrm.thinkerhouse.zkt.terminal.ZKTerminal;

@Controller
public class HomeController {

	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private DevicePunchLogService devicePunchLogService;
	
	@Autowired
	private DeviceUserInfoService deviceUserInfoService;

	
	@GetMapping("/login")
	public String login(Model model) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		String msgString = "Login Successfull!!!";
		model.addAttribute("msgString", msgString);
		return "admin/home";
		
	}
	
	@GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to ZKTeco Interface");
        return "admin/home";
    }
	
	@GetMapping("/request")
	public String requests() {
		return "admin/request";
		
	}
	@GetMapping("/salaysheet")
	public String salarySheets() {
		return "admin/salary";
		
	}
	
	//----------- Attendance Devices User Info code start -----------------//
	@GetMapping("/deviceuserinfo")
	public String deviceUserInfoPage(Model model) {
		
		List<DeviceUserInfo> deviceUserInfos = deviceUserInfoService.getDeviceUserInfos();
		
		model.addAttribute("deviceUserInfos", deviceUserInfos);
		
		return "admin/deviceuserinfo";
		
	}

	@GetMapping("/deviceuserdataform")
	public String deviceUserInfoDownloadPage(Model model) {
		
		List<Devices> devices = deviceService.getDevices();
		
		if (devices != null) {
			Integer deviceLogDownLoadRoute = 2;
			
			model.addAttribute("deviceLogDownLoadRoute", deviceLogDownLoadRoute);
			
			model.addAttribute("devices", devices);
			return "admin/device_log_down_form";
		}
		String msgString = "Device not found!";
		
		model.addAttribute("msgString", msgString);
		
		return "admin/deviceuserinfo";
		
	}
	
	@PostMapping("/download_device_userinfo")
	public String downloadDeviceUserInfo(@RequestParam("deviceId") Integer deviceId, Model model) throws IOException {

		String msgString = "";
		
		Devices devices = deviceService.getDevices(deviceId);
		System.out.println(devices.getDeviceName());
	    String ipAddress = devices.getIpAddress();
	    int port = devices.getPort();
	    
	    ZKTerminal zkTerminal = new ZKTerminal(ipAddress, port);
	    
	   
	    
	    try {
	    	
       
	    	ZKCommandReply connect = zkTerminal.connect();
	    	
	    	System.out.println(connect.getCode());
	    	
	    	List<UserInfo> userList = zkTerminal.getAllUsers();
	    	List<DeviceUserInfo> deviceUserInfos = deviceUserInfoService.getDeviceUserInfos();
	    	
	    	// Count the number of users
	        int numberOfUsers = userList.size();
	        System.out.println("Number of users: " + numberOfUsers);
	        
	        long countDeviceUserInfo = deviceUserInfoService.countDeviceUserInfo();
	    	
	        if (numberOfUsers > countDeviceUserInfo ) {
				
			}
	        
          // Access and print user information
          for (UserInfo user : userList ) {
	             
        	  DeviceUserInfo deviceUserInfo = new DeviceUserInfo();
        	  
        	  if (deviceUserInfoService.getDeviceUserInfoByUserId(user.getUserid()) == null ) {
				
			
        	  
        	  deviceUserInfo.setDevices(devices);
        	  deviceUserInfo.setUserid(user.getUserid());
        	  deviceUserInfo.setName(user.getName());
        	  deviceUserInfo.setPassword(user.getPassword());
        	  deviceUserInfo.setCardno(user.getCardno());
        	  deviceUserInfo.setGroupNumber(user.getGroupNumber());
        	  deviceUserInfo.setUserTimeZoneFlag(user.getUserTimeZoneFlag());
        	  deviceUserInfo.setTimeZone1(user.getTimeZone1());
        	  deviceUserInfo.setTimeZone2(user.getTimeZone2());
        	  deviceUserInfo.setTimeZone3(user.getTimeZone3());
        	  deviceUserInfo.setUid(user.getUid());
        	  deviceUserInfo.setRole(user.getRole().toString());
        	  deviceUserInfo.setCreateDate(new Date());
        	  deviceUserInfo.setUpdateDate(new Date());
        	  deviceUserInfo.setStatus(0);
        	  
        	  deviceUserInfoService.addDeviceUserInfo(deviceUserInfo);
          }
        	  
          }
          msgString = "Download Successfully!";
			
		} catch (IOException | DeviceNotConnectException | ParseException e) {
			msgString = "Got error : " + e.getMessage();
			
		} finally {
				zkTerminal.disconnect();
		}
	    
	    List<DeviceUserInfo> deviceUserInfos = deviceUserInfoService.getDeviceUserInfos();
	    
	    model.addAttribute("msgString", msgString);
	    model.addAttribute("deviceUserInfos", deviceUserInfos);
	    
	    return "admin/deviceuserinfo";
	}
	
	//----------- Attendance Devices User Info code end -----------------//
	
	@GetMapping("/allowences")
	public String allowences() {
		return "admin/allowences";
		
	}
	
	//----------- Attendance Devices code start -----------------//
	
	@GetMapping("/attdevices")
	public String attDevices(Model model) {
		
		List<Devices> allDevices = deviceService.getDevices();
		
		model.addAttribute("allDevices", allDevices);
		
		return "admin/attdevices";
	}
	
	@GetMapping("/new_device")
	public String newDevices() {
		return "admin/new_device";
	}
	
	@GetMapping("/rm_device/{id}")
	public String deleteDevices(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			deviceService.deleteDevice(id);
			
			msgString = "Device removed successfully.";
			
		} catch (Exception e) {
			msgString = e.getMessage();
		}
		List<Devices> allDevices = deviceService.getDevices();
		model.addAttribute("allDevices", allDevices);
		model.addAttribute("msgString", msgString);
		
		return "admin/attdevices";
	}
	
	@PostMapping("/add_device")
	public String addDevice(@ModelAttribute("devices") Devices devices, Model model) {

		String msgString = "";
		
		try {
			
			devices.setCreateDate(new Date());
			devices.setUpdateDate(new Date());
			deviceService.addDevices(devices);
			
			msgString = "Device added successfully!";
			
			
		} catch (Exception e) {

			msgString = e.getMessage();
		}
		List<Devices> allDevices = deviceService.getDevices();
		
		model.addAttribute("msgString", msgString);
		model.addAttribute("allDevices", allDevices);
		
		return "admin/attdevices";
		
	}
	//----------- Attendance Devices code end -----------------//
	//----------- Load Data code start ------------------------//
	
	@GetMapping("/loaddata")
	public String loadData(Model model) {
		List<DevicePunchLog> deviceLogs = devicePunchLogService.getDevicePunchLogs();
		
		model.addAttribute("deviceLogs", deviceLogs);
		
		return "admin/loaddata";
	}

	@GetMapping("/devicelogform")
	public String deviceLogForm(Model model) {
		
		List<Devices> devices = deviceService.getDevices();
		
		if (devices != null) {
			model.addAttribute("devices", devices);
			
			Integer deviceLogDownLoadRoute = 1;
			
			model.addAttribute("deviceLogDownLoadRoute", deviceLogDownLoadRoute);
			
			return "admin/device_log_down_form";
		}
		String msgString = "Device not found!";
		
		model.addAttribute("msgString", msgString);
		
		return "admin/loaddata";
	}

	@PostMapping("/download_logs")
	public String downloadLogs(@RequestParam("deviceId") Integer deviceId, Model model) throws IOException {
	    Devices devices = deviceService.getDevices(deviceId);
	    if (devices == null) {
	        model.addAttribute("msgString", "Device not found for deviceId: " + deviceId);
	        return "admin/loaddata";
	    }
	    
	    String ipAddress = devices.getIpAddress();
	    int port = devices.getPort();

	    ZKTerminal zkTerminal = new ZKTerminal(ipAddress, port);
	    String msgString = "";

	    try {
	        ZKCommandReply connect = zkTerminal.connect();
	        
	        List<DeviceUserInfo> deviceUserInfos = deviceUserInfoService.getDeviceUserInfos();
	        
	        if (deviceUserInfos == null || deviceUserInfos.isEmpty()) {
	            System.out.println("No device user info records found.");
	            model.addAttribute("msgString", "No device user info records found.");
	            return "admin/loaddata";
	        }
	        System.out.println(connect.getCode());

	        List<AttendanceRecord> attendanceRecords = zkTerminal.getAttendanceRecords();
	        System.out.println("Number of attendance records found: " + attendanceRecords.size());
	        
	        
	        if (attendanceRecords == null || attendanceRecords.isEmpty()) {
	            System.out.println("No attendance records found.");
	            model.addAttribute("msgString", "No attendance records found.");
	            return "admin/loaddata";
	        }

	        for (DeviceUserInfo deviceUserInfo : deviceUserInfos) {
	            String userId = deviceUserInfo.getUserid();
	            if (userId != null && !userId.isEmpty()) {
	                System.out.println("Processing userId: " + userId);

	                for (AttendanceRecord attendanceRecord : attendanceRecords) {
	                    System.out.println("Checking record for userID: " + attendanceRecord.getUserID());
	                    
	                    if (attendanceRecord.getUserID() != null && userId.trim().equals(attendanceRecord.getUserID().trim())) {
	                        if (!isDuplicate(attendanceRecord, devices)) {
	                            DevicePunchLog punchLog = createPunchLog(attendanceRecord, devices);
	                            devicePunchLogService.addDevicePunchLog(punchLog);
	                        } else {
	                            System.out.println("Duplicate record found, skipping insert.");
	                        }
	                    }
	                }
	            }
	        }
	    } catch (IOException | DeviceNotConnectException | ParseException e) {
	        // Log the exception
	        System.out.println("Error occurred while downloading logs: " + e);
	        
	        msgString = "Error occurred while downloading logs: " + e.getMessage();
	        // Add error message to the model
	        model.addAttribute("msgString", msgString);
	    } catch (Exception e) {
	        // Log any other exception
	        System.out.println("Unexpected error: " + e);
	        msgString = "Unexpected error: " + e.getMessage();
	        model.addAttribute("msgString", msgString);
	    } finally {
	        // Ensure the terminal is disconnected
	        zkTerminal.disconnect();
	    }

	    return "admin/loaddata";
	}

	
	
	
	
	// Method to check for duplicate record
	private boolean isDuplicate(AttendanceRecord attendanceRecord, Devices devices) {
	    // Replace this with actual code to check the database for existing record
	    List<DevicePunchLog> existingLogs = devicePunchLogService.getDevicePunchLogByUserIDAndRecordTimeAndDevices(
	        attendanceRecord.getUserID(), attendanceRecord.getRecordTime(), devices);

	    return existingLogs != null && !existingLogs.isEmpty();
	}

	private DevicePunchLog createPunchLog(AttendanceRecord attendanceRecord, Devices devices) {
	    DevicePunchLog punchLog = new DevicePunchLog();
	    punchLog.setUserID(attendanceRecord.getUserID());
	    punchLog.setCreateDate(new Date());
	    punchLog.setUpdateDate(new Date());
	    punchLog.setDevices(devices);
	    punchLog.setRecordTime(attendanceRecord.getRecordTime());
	    punchLog.setVerifyState(attendanceRecord.getVerifyState().toString());
	    punchLog.setVerifyType(attendanceRecord.getVerifyType().toString());
	    return punchLog;
	}



	
	//----------- Load Data code start ------------------------//
	
	@GetMapping("/about")
	public String aboutInfo() {
		return "admin/about";
	}
}