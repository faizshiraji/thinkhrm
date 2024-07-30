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
@Table(name = "salary_structure")
public class SalaryStructure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_salary_structure")
	private int idSalaryStructure;
	@Column(name = "basic_salary")
	private Double basicSalary;
	private Double medical;
	@Column(name = "house_rent")
	private Double houseRent;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_employee")
	private Employee employee;

	private Integer status;
	
	public SalaryStructure() {

	}

	public int getIdSalaryStructure() {
		return idSalaryStructure;
	}

	public void setIdSalaryStructure(int idSalaryStructure) {
		this.idSalaryStructure = idSalaryStructure;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getMedical() {
		return medical;
	}

	public void setMedical(Double medical) {
		this.medical = medical;
	}

	public Double getHouseRent() {
		return houseRent;
	}

	public void setHouseRent(Double houseRent) {
		this.houseRent = houseRent;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SalaryStructure [idSalaryStructure=" + idSalaryStructure + ", basicSalary=" + basicSalary + ", medical="
				+ medical + ", houseRent=" + houseRent + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", employee=" + employee + ", status=" + status + "]";
	}

	
	
	
}
