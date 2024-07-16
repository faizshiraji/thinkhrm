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

@Entity
public class Deductions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_deductions")
	private int idDeductions;
	private String deductionName;
	private Double amount;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_employee")
	private Employee employee;
	
	public Deductions() {

	}

	public int getIdDeductions() {
		return idDeductions;
	}

	public void setIdDeductions(int idDeductions) {
		this.idDeductions = idDeductions;
	}

	public String getDeductionName() {
		return deductionName;
	}

	public void setDeductionName(String deductionName) {
		this.deductionName = deductionName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Deductions(int idDeductions, String deductionName, Double amount, Date createDate, Date updateDate,
			Employee employee) {
		super();
		this.idDeductions = idDeductions;
		this.deductionName = deductionName;
		this.amount = amount;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Deductions [idDeductions=" + idDeductions + ", deductionName=" + deductionName + ", amount=" + amount
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", employee=" + employee + "]";
	}
	
	
}
