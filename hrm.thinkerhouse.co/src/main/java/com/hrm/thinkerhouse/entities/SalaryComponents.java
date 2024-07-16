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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "salary_components")
public class SalaryComponents {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salary_component")
	private int idSalaryComponent;
	private String compomentName;
	private Double amount;
	private int status;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	private Employee employee;
	
	public SalaryComponents() {

	}

	public int getIdSalaryComponent() {
		return idSalaryComponent;
	}

	public void setIdSalaryComponent(int idSalaryComponent) {
		this.idSalaryComponent = idSalaryComponent;
	}

	public String getCompomentName() {
		return compomentName;
	}

	public void setCompomentName(String compomentName) {
		this.compomentName = compomentName;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public SalaryComponents(int idSalaryComponent, String compomentName, Double amount, int status, Date createDate,
			Date updateDate, Employee employee) {
		super();
		this.idSalaryComponent = idSalaryComponent;
		this.compomentName = compomentName;
		this.amount = amount;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "SalaryComponents [idSalaryComponent=" + idSalaryComponent + ", compomentName=" + compomentName
				+ ", amount=" + amount + ", status=" + status + ", createDate=" + createDate + ", updateDate="
				+ updateDate + ", employee=" + employee + "]";
	}
	
	
}
