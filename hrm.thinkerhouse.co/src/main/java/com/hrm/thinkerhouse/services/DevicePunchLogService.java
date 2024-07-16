package com.hrm.thinkerhouse.services;

import java.util.Date;
import java.util.List;


import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Devices;

public interface DevicePunchLogService {

	public List<DevicePunchLog> getDevicePunchLogs();
	public DevicePunchLog getDevicePunchLog(int idDevicePunchLog);
	List<DevicePunchLog> getDevicePunchLogByUserID(String userID);
	List<DevicePunchLog> getDevicePunchLogsByDateRange(Date startDate, Date endDate);
	List<DevicePunchLog> getDevicePunchLogByUserIDAndRecordTimeAndDevices(String userID, Date recordTime, Devices devices);
	List<DevicePunchLog> getDevicePUnchLogByStatus(int logStatus);
	public DevicePunchLog addDevicePunchLog(DevicePunchLog devicePunchLog);
	public DevicePunchLog updateDevicePunchLog(DevicePunchLog devicePunchLog);
	public void deleteDevicePunchLog(int idDevicePunchLog);
	public long countDevicePunchLog();
	
}
