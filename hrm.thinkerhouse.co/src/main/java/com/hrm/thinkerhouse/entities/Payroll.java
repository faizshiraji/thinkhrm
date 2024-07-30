package com.hrm.thinkerhouse.entities;

import java.util.Date;
import java.util.List;

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
import jakarta.persistence.OneToMany;

@Entity
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_payroll")
	private int idPayroll;
	private int month;
	@Column(name = "basic_salary")
	private Double basicSalary;
	@Column(name = "total_allowences")
	private Double totalAllowences;
	@Column(name = "total_deductions")
	private Double totalDeductions;
	@Column(name = "net_salary")
	private Double netSalary;
	private String approvedBy;
	private int status;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "id_employee")
	private Employee employee;
	
	@ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST})
	@JoinColumn(name = "id_users")
	private Users users;
	
	@OneToMany(mappedBy = "payroll")
	private List<PaymentHistory> paymentHistories;
	
	public Payroll() {

	}

	public int getIdPayroll() {
		return idPayroll;
	}

	public void setIdPayroll(int idPayroll) {
		this.idPayroll = idPayroll;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Double getTotalAllowences() {
		return totalAllowences;
	}

	public void setTotalAllowences(Double totalAllowences) {
		this.totalAllowences = totalAllowences;
	}

	public Double getTotalDeductions() {
		return totalDeductions;
	}

	public void setTotalDeductions(Double totalDeductions) {
		this.totalDeductions = totalDeductions;
	}

	public Double getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Double netSalary) {
		this.netSalary = netSalary;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
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

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public List<PaymentHistory> getPaymentHistories() {
		return paymentHistories;
	}

	public void setPaymentHistories(List<PaymentHistory> paymentHistories) {
		this.paymentHistories = paymentHistories;
	}

	@Override
	public String toString() {
		return "Payroll [idPayroll=" + idPayroll + ", month=" + month + ", basicSalary=" + basicSalary
				+ ", totalAllowences=" + totalAllowences + ", totalDeductions=" + totalDeductions + ", netSalary="
				+ netSalary + ", approvedBy=" + approvedBy + ", status=" + status + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + ", employee=" + employee + ", users=" + users + ", paymentHistories="
				+ paymentHistories + "]";
	}

	
	
	
}
