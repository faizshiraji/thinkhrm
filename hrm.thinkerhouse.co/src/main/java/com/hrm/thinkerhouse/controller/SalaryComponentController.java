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
import com.hrm.thinkerhouse.entities.SalaryComponents;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.SalaryComponentsService;

@Controller
public class SalaryComponentController {

	@Autowired
	private SalaryComponentsService salaryComponentsService;
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/salarycompoment")
	public String viewSalaryComponent( Model model) {
		
		List<SalaryComponents> salaryComponents = salaryComponentsService.getSalaryComponents();
		
		model.addAttribute("salaryComponents", salaryComponents);
		
		return "admin/salarycompoment";
		
	}
	
	@GetMapping("/new_salary_component")
	public String newSalaryComponent(Model model) {
		
		SalaryComponents salaryComponent = new SalaryComponents();
		List<Employee> employeeByStatus = employeeService.getEmployeeByStatus(1);
		
		model.addAttribute("employeeByStatus", employeeByStatus);
		model.addAttribute("salaryComponent", salaryComponent);
		
		
		return "admin/new_salary_component";
		
	}
	
	@PostMapping("/add_salary_component")
	public String addSalaryComponent(
			@ModelAttribute("") SalaryComponents salaryComponent,
			Model model) {
		
		String msgString = "";
		
		salaryComponent.setCreateDate(new Date());
		salaryComponent.setUpdateDate(new Date());
		
		salaryComponentsService.addSalaryComponents(salaryComponent);
		
		msgString = "Your salary component added successfully.";
		
		model.addAttribute("msgString", msgString);
		
		return "admin/salarycompoment";
		
	}
	
	@GetMapping("/edit_salaryComponent/{id}")
	public String editSalaryComponent(@PathVariable("id") Integer id, Model model ) {
		
		SalaryComponents salaryComponent = salaryComponentsService.getSalaryComponents(id);
		
		
		List<Employee> employeeByStatus = employeeService.getEmployeeByStatus(1);
		
		model.addAttribute("employeeByStatus", employeeByStatus);
		model.addAttribute("salaryComponent", salaryComponent);
		
		return "admin/new_salary_component";
		
	}
	
	@PostMapping("/updatesalarycomponent/{id}")
	public String updateSalaryComponent(@PathVariable("id") Integer id, 
										@ModelAttribute("salarycomponent") SalaryComponents salaryComponent, 
										Model model) {
		SalaryComponents editSalaryComponent = salaryComponentsService.getSalaryComponents(id);
		
		editSalaryComponent.setEmployee(salaryComponent.getEmployee());
		editSalaryComponent.setCompomentName(salaryComponent.getCompomentName());
		editSalaryComponent.setAmount(salaryComponent.getAmount());
		editSalaryComponent.setStatus(salaryComponent.getStatus());
		editSalaryComponent.setUpdateDate(new Date());
		
		salaryComponentsService.updateSalaryComponents(editSalaryComponent);
		
		String msgString = "Salary Component updated successfully.";
		
		List<SalaryComponents> salaryComponents = salaryComponentsService.getSalaryComponents();
		
		model.addAttribute("salaryComponents", salaryComponents);
		
		model.addAttribute("msgString", msgString);
		
		return "admin/salarycompoment";
		
	}
	
	@GetMapping("/del_salaryComponent/{id}")
	public String deleteSalaryComponent(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			salaryComponentsService.deleteSalaryComponents(id);
			
			msgString = "You have deleted successfully Salary Component ID: " + id;
			
		} catch (Exception e) {

			msgString = "You have encountered an error: " + e.getMessage();
		
		}
		

		
		List<SalaryComponents> salaryComponents = salaryComponentsService.getSalaryComponents();
		
		model.addAttribute("salaryComponents", salaryComponents);
		model.addAttribute("msgString", msgString);
		return "admin/salarycompoment";
		
	}
	
}
