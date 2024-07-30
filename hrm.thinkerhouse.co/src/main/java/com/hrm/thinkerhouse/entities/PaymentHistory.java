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
@Table(name = "payment_history")
public class PaymentHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_payment_history")
	private int idPaymentHistory;
	@DateTimeFormat(iso = ISO.DATE)
	private Date paymentDate;
	private String paymentBy;
	private String reference;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_employee")
	private Employee employee;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_payroll")
	private Payroll payroll;
	private Integer status;
	public PaymentHistory() {
		
	}
	public int getIdPaymentHistory() {
		return idPaymentHistory;
	}
	public void setIdPaymentHistory(int idPaymentHistory) {
		this.idPaymentHistory = idPaymentHistory;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public String getPaymentBy() {
		return paymentBy;
	}
	public void setPaymentBy(String paymentBy) {
		this.paymentBy = paymentBy;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
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
	public Payroll getPayroll() {
		return payroll;
	}
	public void setPayroll(Payroll payroll) {
		this.payroll = payroll;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PaymentHistory [idPaymentHistory=" + idPaymentHistory + ", paymentDate=" + paymentDate + ", paymentBy="
				+ paymentBy + ", reference=" + reference + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", employee=" + employee + ", payroll=" + payroll + ", status=" + status + "]";
	}

}
