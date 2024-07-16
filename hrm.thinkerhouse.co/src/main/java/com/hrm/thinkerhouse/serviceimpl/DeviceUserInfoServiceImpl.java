package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.DeviceUserInfo;
import com.hrm.thinkerhouse.repo.DeviceUserInfoRepo;
import com.hrm.thinkerhouse.services.DeviceUserInfoService;

@Service
public class DeviceUserInfoServiceImpl implements DeviceUserInfoService {

	@Autowired
	public DeviceUserInfoRepo deviceUserInfoRepo;
	
	@Override
	public List<DeviceUserInfo> getDeviceUserInfos() {
		return deviceUserInfoRepo.findAll();
	}

	@Override
	public DeviceUserInfo getDeviceUserInfo(int idDeviceUserInfo) {
		return deviceUserInfoRepo.findById(idDeviceUserInfo).get();
	}

	@Override
	public DeviceUserInfo getDeviceUserInfoByUserId(String userid) {
		return deviceUserInfoRepo.findByUserId(userid);
	}

	@Override
	public DeviceUserInfo addDeviceUserInfo(DeviceUserInfo deviceUserInfo) {
		return deviceUserInfoRepo.save(deviceUserInfo);
	}

	@Override
	public DeviceUserInfo updateDeviceUserInfo(DeviceUserInfo deviceUserInfo) {
		return deviceUserInfoRepo.save(deviceUserInfo);
	}

	@Override
	public void deleteDeviceUserInfo(int idDeviceUserInfo) {
		deviceUserInfoRepo.deleteById(idDeviceUserInfo);
	}

	@Override
	public long countDeviceUserInfo() {
		return deviceUserInfoRepo.count();
	}

	@Override
	public List<DeviceUserInfo> getDeviceUserInfoByStatus(int status) {
		return deviceUserInfoRepo.findByStatus(status);
	}

}
