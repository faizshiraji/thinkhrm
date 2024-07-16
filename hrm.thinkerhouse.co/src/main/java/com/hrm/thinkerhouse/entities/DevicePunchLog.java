package com.hrm.thinkerhouse.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "device_punch_log")
public class DevicePunchLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_device_punch_log")
	private int idDevicePunchLog;
	@Column(name = "user_id")
	private String userID;
	@Column(name = "user_sn")
	private String userSN;
	@Column(name = "record_time")
	private Date recordTime;
	@Column(name = "verify_type")
	private String verifyType;
	@Column(name = "verify_state")
	private String verifyState;
	@Column(name = "log_status")
	private int logStatus;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_device")
	private Devices devices;
	
	public DevicePunchLog() {

	}

	public int getIdDevicePunchLog() {
		return idDevicePunchLog;
	}

	public void setIdDevicePunchLog(int idDevicePunchLog) {
		this.idDevicePunchLog = idDevicePunchLog;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserSN() {
		return userSN;
	}

	public void setUserSN(String userSN) {
		this.userSN = userSN;
	}

	public Date getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}

	public String getVerifyType() {
		return verifyType;
	}

	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}

	public String getVerifyState() {
		return verifyState;
	}

	public void setVerifyState(String verifyState) {
		this.verifyState = verifyState;
	}

	public int getLogStatus() {
		return logStatus;
	}

	public void setLogStatus(int logStatus) {
		this.logStatus = logStatus;
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

	public Devices getDevices() {
		return devices;
	}

	public void setDevices(Devices devices) {
		this.devices = devices;
	}

	@Override
	public String toString() {
		return "DevicePunchLog [idDevicePunchLog=" + idDevicePunchLog + ", userID=" + userID + ", userSN=" + userSN
				+ ", recordTime=" + recordTime + ", verifyType=" + verifyType + ", verifyState=" + verifyState
				+ ", logStatus=" + logStatus + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", devices=" + devices + "]";
	}

	

}
