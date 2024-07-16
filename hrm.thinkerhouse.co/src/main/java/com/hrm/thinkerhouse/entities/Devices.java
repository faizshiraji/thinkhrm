package com.hrm.thinkerhouse.entities;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Devices {

	@Id
	@Column(name = "id_device")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDevice;
	@Column(unique = true)
	private String deviceName;
	private String ipAddress;
	private String machineId;
	private int port;
	private int status;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@OneToMany(mappedBy = "devices")
	private List<DevicePunchLog> devicePunchLogs;

	@OneToMany(mappedBy = "devices")
	private List<DeviceUserInfo> deviceUserInfos;
	
	public Devices() {

	}

	public int getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(int idDevice) {
		this.idDevice = idDevice;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getMachineId() {
		return machineId;
	}

	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public List<DevicePunchLog> getDevicePunchLogs() {
		return devicePunchLogs;
	}

	public void setDevicePunchLogs(List<DevicePunchLog> devicePunchLogs) {
		this.devicePunchLogs = devicePunchLogs;
	}

	public List<DeviceUserInfo> getDeviceUserInfos() {
		return deviceUserInfos;
	}

	public void setDeviceUserInfos(List<DeviceUserInfo> deviceUserInfos) {
		this.deviceUserInfos = deviceUserInfos;
	}

	

	
}
