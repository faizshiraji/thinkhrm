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
@Table(name = "attendance_log")
public class AttendanceLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_attendance_log")
	private int idAttendanceLog;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(nullable = true)
	private Date inTime;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date outTime;
	private String attendStatus;
	private String note;
	@Column(nullable = true)
	private Integer status;
	@Column(nullable = true)
	private Integer inId;
	private Integer outId;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_employee")
	private Employee employee;
	
	public AttendanceLog() {

	}

	public int getIdAttendanceLog() {
		return idAttendanceLog;
	}

	public void setIdAttendanceLog(int idAttendanceLog) {
		this.idAttendanceLog = idAttendanceLog;
	}

	public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public Date getOutTime() {
		return outTime;
	}

	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}

	public String getAttendStatus() {
		return attendStatus;
	}

	public void setAttendStatus(String attendStatus) {
		this.attendStatus = attendStatus;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public int getInId() {
		return inId;
	}

	public void setInId(Integer inId) {
		this.inId = inId;
	}

	public int getOutId() {
		return outId;
	}

	public void setOutId(Integer outId) {
		this.outId = outId;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public AttendanceLog(int idAttendanceLog, Date inTime, Date outTime, String attendStatus, String note, Integer status,
			Integer inId, Integer outId, Employee employee) {
		super();
		this.idAttendanceLog = idAttendanceLog;
		this.inTime = inTime;
		this.outTime = outTime;
		this.attendStatus = attendStatus;
		this.note = note;
		this.status = status;
		this.inId = inId;
		this.outId = outId;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "AttendanceLog [idAttendanceLog=" + idAttendanceLog + ", inTime=" + inTime + ", outTime=" + outTime
				+ ", attendStatus=" + attendStatus + ", note=" + note + ", status=" + status + ", inId=" + inId
				+ ", outId=" + outId + ", employee=" + employee + "]";
	}

	
}
