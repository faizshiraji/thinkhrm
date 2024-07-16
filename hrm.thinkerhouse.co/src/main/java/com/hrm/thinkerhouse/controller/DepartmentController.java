package com.hrm.thinkerhouse.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrm.thinkerhouse.entities.Department;
import com.hrm.thinkerhouse.services.DepartpentServices;

@Controller
public class DepartmentController {

	@Autowired
	private DepartpentServices departpentServices;
	
	@GetMapping("/department")
	public String department(Model model) {
		
		List<Department> departments = departpentServices.getDepartments();
		
		model.addAttribute("departments", departments);
		
		return "admin/departments";
		
	}
	
	
	@GetMapping("/new_department")
	public String newDepartment() {
		
		return "admin/new_department";
		
	}

	@PostMapping("/add_department")
	public String appDepartment(@ModelAttribute("department") Department department, Model model) {
		
		String msgString = "";
		
		try {
			department.setCreateDate(new Date());
			department.setUpdateDate(new Date());
			
			departpentServices.addDepartment(department);
			
			msgString = "Department added successfully";
		
		} catch (Exception e) {

		msgString = "We encountered some error: " + e.getMessage();
			
		}
		
		List<Department> departments = departpentServices.getDepartments();
		
		model.addAttribute("departments", departments); 
		model.addAttribute("msgString", msgString);
		
		return "admin/departments";
		
	}

	@PostMapping("/update_department/{id}")
	public String updateDepartment(@PathVariable("id") Integer id, Department department, Model model) {
		
		String msgString = "";
		
		try {
			Department editDepartment = departpentServices.getDepartment(id);
			
			editDepartment.setDepartmentName(department.getDepartmentName());
			editDepartment.setStatus(department.getStatus());
			editDepartment.setUpdateDate(new Date());
			
			departpentServices.updateDepartment(editDepartment);
			
			msgString = "Department updated successfully.";
		
		
		} catch (Exception e) {

			msgString = "We encounted error: " + e.getMessage();
			
		}
		
		List<Department> departments = departpentServices.getDepartments();
		
		model.addAttribute("departments", departments);
		model.addAttribute("msgString", msgString);
		
		return "admin/departments";
		
	}

	@GetMapping("/del_department/{id}")
	public String delDepartment(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			
			departpentServices.deleteDepartment(id);
			
			msgString = "Department deleted successfully.";
			
		} catch (Exception e) {

			msgString = "Getting error to complete this deletion";
		
		}

		List<Department> departments = departpentServices.getDepartments();
		
		model.addAttribute("departments", departments);
		model.addAttribute("msgString", msgString);
		
		return "admin/departments";
		
	}
	
	@GetMapping("/edit_department/{id}")
	public String editDepartment(@PathVariable("id") Integer id, Model model) {
		
		Department department = departpentServices.getDepartment(id);
		
		model.addAttribute("department", department);
		
		return "admin/new_department";
		
	}
	
	
}
