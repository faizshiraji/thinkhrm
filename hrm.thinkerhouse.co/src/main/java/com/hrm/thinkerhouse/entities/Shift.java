package com.hrm.thinkerhouse.entities;

import java.sql.Time;
import java.time.LocalTime;
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
public class Shift {

	@Id
	@Column(name = "id_shift")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idShift;
	@Column(unique = true)
	private String shiftName;
	@Column(unique = true)
	private String shiftShortName;
	private String description;
	@DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime startTime;

    @DateTimeFormat(pattern = "HH:mm:ss")
    private LocalTime endTime;
	private boolean hideEndTime;
	private Time breakTime;
	private int status;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@OneToMany(mappedBy = "shift")
	private List<Employee> employee;
	
	public Shift() {

	}

	public int getIdShift() {
		return idShift;
	}

	public void setIdShift(int idShift) {
		this.idShift = idShift;
	}

	public String getShiftName() {
		return shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}

	public String getShiftShortName() {
		return shiftShortName;
	}

	public void setShiftShortName(String shiftShortName) {
		this.shiftShortName = shiftShortName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public boolean isHideEndTime() {
		return hideEndTime;
	}

	public void setHideEndTime(boolean hideEndTime) {
		this.hideEndTime = hideEndTime;
	}

	public Time getBreakTime() {
		return breakTime;
	}

	public void setBreakTime(Time breakTime) {
		this.breakTime = breakTime;
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

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Shift [idShift=" + idShift + ", shiftName=" + shiftName + ", shiftShortName=" + shiftShortName
				+ ", description=" + description + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", hideEndTime=" + hideEndTime + ", breakTime=" + breakTime + ", status=" + status + ", createDate="
				+ createDate + ", updateDate=" + updateDate + ", employee=" + employee + "]";
	}

	
}
