package com.hrm.thinkerhouse.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_users")
	private int idUsers;
	@Column(unique = true)
	private String username;
	private String password;
	private String status;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createDate;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date updateDate;
	
	@ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
	@JoinColumn(name = "id_role")
	private Role role;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_employee")
    private Employee employee;
	
	public Users() {
		
	}

	public int getIdUsers() {
		return idUsers;
	}

	public void setIdUsers(int idUsers) {
		this.idUsers = idUsers;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Users(int idUsers, String username, String password, String status, Date createDate, Date updateDate,
			Role role) {
		super();
		this.idUsers = idUsers;
		this.username = username;
		this.password = password;
		this.status = status;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Users [idUsers=" + idUsers + ", username=" + username + ", password=" + password + ", status=" + status
				+ ", createDate=" + createDate + ", updateDate=" + updateDate + ", role=" + role + "]";
	}
	
	
	
}
