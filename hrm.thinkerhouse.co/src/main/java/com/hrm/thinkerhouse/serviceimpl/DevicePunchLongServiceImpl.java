package com.hrm.thinkerhouse.serviceimpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Devices;
import com.hrm.thinkerhouse.repo.DevicePunchLogRepo;
import com.hrm.thinkerhouse.services.DevicePunchLogService;

@Service
public class DevicePunchLongServiceImpl implements DevicePunchLogService {

    @Autowired
    public DevicePunchLogRepo devicePunchLogRepo;
    
    @Override
    public List<DevicePunchLog> getDevicePunchLogs() {
        return devicePunchLogRepo.findAll();
    }

    @Override
    public DevicePunchLog getDevicePunchLog(int idDevicePunchLog) {
        return devicePunchLogRepo.findById(idDevicePunchLog).get();
    }

    @Override
    public DevicePunchLog addDevicePunchLog(DevicePunchLog devicePunchLog) {
        return devicePunchLogRepo.save(devicePunchLog);
    }

    @Override
    public DevicePunchLog updateDevicePunchLog(DevicePunchLog devicePunchLog) {
        return devicePunchLogRepo.save(devicePunchLog);
    }

    @Override
    public void deleteDevicePunchLog(int idDevicePunchLog) {
        devicePunchLogRepo.deleteById(idDevicePunchLog);
    }

    @Override
    public long countDevicePunchLog() {
        return devicePunchLogRepo.count();
    }

    @Override
    public List<DevicePunchLog> getDevicePunchLogsByDateRange(Date startDate, Date endDate) {
        return devicePunchLogRepo.findByRecordTimeBetween(startDate, endDate);
    }

    @Override
    public List<DevicePunchLog> getDevicePunchLogByUserID(String userID) {
        return devicePunchLogRepo.findByUserID(userID);
    }

    @Override
    public List<DevicePunchLog> getDevicePunchLogByUserIDAndRecordTimeAndDevices(String userID, Date recordTime,
            Devices devices) {
        return devicePunchLogRepo.findByUserIDAndRecordTimeAndDevices(userID, recordTime, devices);
    }

    public List<DevicePunchLog> getDevicePunchLogsByStatus(int status) {
        return devicePunchLogRepo.findByStatus(status);
    }

	@Override
	public List<DevicePunchLog> getDevicePUnchLogByStatus(int logStatus) {
		return devicePunchLogRepo.findByStatus(logStatus);
	}
}
