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

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.SalaryStructure;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.SalaryStructureService;

@Controller
public class SalaryStructureController {

	
	@Autowired
	private SalaryStructureService salaryStructureService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/salarystructure")
	public String viewSalaryStructure(Model model) {
		
		List<SalaryStructure> salaryStructures = salaryStructureService.getSalaryStructures();
		
		model.addAttribute("salaryStructures", salaryStructures);
		
	return "admin/salarystructure";
		
	}

	@GetMapping("/new_salary_structure")
	public String newSalaryStructure(Model model) {
		
		List<Employee> employeeByStatus = employeeService.getEmployeeByStatus(1);
		
		SalaryStructure salaryStructure = new SalaryStructure();
		
		model.addAttribute("employeeByStatus", employeeByStatus);
		
		model.addAttribute("salaryStructure", salaryStructure);
		
		model.addAttribute("code", 0);
		
		
		return "admin/new_salaryStructure";
		
	}
	
	@PostMapping("/add_salary_structure")
	public String addSalaryStructure(
			@ModelAttribute("salarystructure") SalaryStructure salaryStructure,
			Model model) {
		
		String msgString = "";

		salaryStructure.setCreateDate(new Date());
		salaryStructure.setUpdateDate(new Date());
		
		salaryStructureService.addSalaryStructure(salaryStructure);
				
		msgString = "Your salary structure added successfully.";
		
		List<SalaryStructure> salaryStructures = salaryStructureService.getSalaryStructures();
		
		model.addAttribute("salaryStructures", salaryStructures);
		model.addAttribute("msgString", msgString);
		
		return "admin/salarystructure";
		
	}
	
	@GetMapping("/edit_salaryStructure/{id}")
	public String editSalaryStructure(
									@PathVariable("id") Integer id,
									Model model) {
		
		SalaryStructure salaryStructure = salaryStructureService.getSalaryStructur(id);
		List<Employee> employeeByStatus = employeeService.getEmployeeByStatus(1);
		
		model.addAttribute("employeeByStatus", employeeByStatus);
		model.addAttribute("salaryStructure", salaryStructure);
		model.addAttribute("code", 1);
		
		return "admin/new_salaryStructure";
		
	}
	
	@PostMapping("/update_salary_structure/{id}")
	public String updateSalaryStructure(@PathVariable("id") Integer id, 
										@ModelAttribute("salaryStructure") SalaryStructure salaryStructure, 
										Model model) {
		SalaryStructure editSalaryStructur = salaryStructureService.getSalaryStructur(id);
		editSalaryStructur.setEmployee(salaryStructure.getEmployee());
		editSalaryStructur.setBasicSalary(salaryStructure.getBasicSalary());
		editSalaryStructur.setHouseRent(salaryStructure.getHouseRent());
		editSalaryStructur.setMedical(salaryStructure.getMedical());
		editSalaryStructur.setUpdateDate(new Date());
		
		salaryStructureService.updateSalaryStructure(editSalaryStructur);
		
		String msgString = "Salary Structure updated successfully.";

		List<SalaryStructure> salaryStructures = salaryStructureService.getSalaryStructures();
		
		model.addAttribute("msgString", msgString);
		model.addAttribute("salaryStructures", salaryStructures);
		
		return "admin/salarystructure";
		
	}
	
	@GetMapping("/del_salaryStructure/{id}")
	public String deleteSalaryStructure(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			salaryStructureService.deleteSalaryStructure(id);
			
			msgString = "Your selected Salary Structure deleted successfully.";
		} catch (Exception e) {

			msgString = "We have entcountered an error: " + e.getMessage();
		
		}
		
		List<SalaryStructure> salaryStructures = salaryStructureService.getSalaryStructures();
		
		model.addAttribute("salaryStructures", salaryStructures);
		
		model.addAttribute("msgString", msgString);
		
		return "admin/salarystructure";
		
	}
	
	
}
