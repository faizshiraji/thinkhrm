package com.hrm.thinkerhouse.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DeviceUserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_device_user_info")
	private int idDeviceUserInfo;
	private int uid;
    private String role;
    private String password;
    private String name;
    private long cardno;
    private String userid;
    private String groupNumber;
    private int userTimeZoneFlag;
    private int timeZone1;
    private int timeZone2;
    private int timeZone3;
	private boolean enabled;
	private Date createDate;
	private Date updateDate;
	private int status;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_device")
	private Devices devices;

	public DeviceUserInfo() {
	}

	public int getIdDeviceUserInfo() {
		return idDeviceUserInfo;
	}

	public void setIdDeviceUserInfo(int idDeviceUserInfo) {
		this.idDeviceUserInfo = idDeviceUserInfo;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getCardno() {
		return cardno;
	}

	public void setCardno(long cardno) {
		this.cardno = cardno;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public int getUserTimeZoneFlag() {
		return userTimeZoneFlag;
	}

	public void setUserTimeZoneFlag(int userTimeZoneFlag) {
		this.userTimeZoneFlag = userTimeZoneFlag;
	}

	public int getTimeZone1() {
		return timeZone1;
	}

	public void setTimeZone1(int timeZone1) {
		this.timeZone1 = timeZone1;
	}

	public int getTimeZone2() {
		return timeZone2;
	}

	public void setTimeZone2(int timeZone2) {
		this.timeZone2 = timeZone2;
	}

	public int getTimeZone3() {
		return timeZone3;
	}

	public void setTimeZone3(int timeZone3) {
		this.timeZone3 = timeZone3;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		status = status;
	}

	public Devices getDevices() {
		return devices;
	}

	public void setDevices(Devices devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "DeviceUserInfo [idDeviceUserInfo=" + idDeviceUserInfo + ", uid=" + uid + ", role=" + role
				+ ", password=" + password + ", name=" + name + ", cardno=" + cardno + ", userid=" + userid
				+ ", groupNumber=" + groupNumber + ", userTimeZoneFlag=" + userTimeZoneFlag + ", timeZone1=" + timeZone1
				+ ", timeZone2=" + timeZone2 + ", timeZone3=" + timeZone3 + ", enabled=" + enabled + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", Status=" + status + ", devices=" + devices + "]";
	}
	
	
}
