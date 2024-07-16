package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.DeviceUserInfo;

public interface DeviceUserInfoService {

	public List<DeviceUserInfo> getDeviceUserInfos();
	public DeviceUserInfo getDeviceUserInfo(int idDeviceUserInfo);
	public DeviceUserInfo getDeviceUserInfoByUserId(String userid);
	public List<DeviceUserInfo> getDeviceUserInfoByStatus(int status);
	public DeviceUserInfo addDeviceUserInfo(DeviceUserInfo deviceUserInfo);
	public DeviceUserInfo updateDeviceUserInfo(DeviceUserInfo deviceUserInfo);
	public void deleteDeviceUserInfo(int idDeviceUserInfo);
	public long countDeviceUserInfo();
	
}
