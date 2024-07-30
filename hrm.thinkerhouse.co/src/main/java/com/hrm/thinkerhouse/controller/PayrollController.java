package com.hrm.thinkerhouse.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.Payroll;
import com.hrm.thinkerhouse.entities.SalaryComponents;
import com.hrm.thinkerhouse.entities.SalaryStructure;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.PayrollService;
import com.hrm.thinkerhouse.services.SalaryComponentsService;
import com.hrm.thinkerhouse.services.SalaryStructureService;


@Controller
public class PayrollController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private SalaryComponentsService salaryComponentsService;
	
	@Autowired
	private SalaryStructureService salaryStructureService;
	
	@Autowired
	private PayrollService payrollService;
	
	@GetMapping("/payroll")
	public String viewPayroll() {
		return "admin/payroll";
		
	}
	
	@GetMapping("/new_payroll")
	public String newPayroll(Model model) {
		
		List<Employee> employeeByStatus = employeeService.getEmployeeByStatus(1);
		
		Payroll payroll = new Payroll();
		
		Employee employee = new Employee();
		
		model.addAttribute("payroll", payroll);
		model.addAttribute("employeeByStatus", employeeByStatus);
		model.addAttribute("employee", employee);
		
		return "admin/new_payroll";
		
	}
	
	@PostMapping("/collect_payroll")
	public String collectPayroll(@ModelAttribute("payroll") Payroll payroll, Model model) {
		
		Double sumAmountAllowences = 0.0;
		Double grossSalary = 0.0;
		
		String msgString = "";
		
		List<SalaryComponents> salaryComponentsByEmployee = salaryComponentsService.getSalaryComponentsByEmployee(payroll.getEmployee());
		
		ArrayList<Double> amountsAllowences = new ArrayList<Double>();
		
		for (Iterator iterator = salaryComponentsByEmployee.iterator(); iterator.hasNext();) {
			SalaryComponents salaryComponents = (SalaryComponents) iterator.next();
			
			amountsAllowences.add(salaryComponents.getAmount());
			
		}
		
		for (Double double1 : amountsAllowences) {
			
			sumAmountAllowences += double1;
		}
		
		List<SalaryStructure> salaryStructureByEmployee = salaryStructureService.getSalaryStructureByEmployee(payroll.getEmployee());		

		for (SalaryStructure salaryStructure : salaryStructureByEmployee) {
			
			if (salaryStructure.getStatus() == 1) {
				
				grossSalary = 	salaryStructure.getBasicSalary() + 
								salaryStructure.getHouseRent() + 
								salaryStructure.getMedical();
			
				System.out.println(grossSalary + "is Gross Salary");
			}
		}
		
		
		
		System.out.println(sumAmountAllowences);
		
		System.out.println(grossSalary);
		
		return "admin/new_payroll";
		
	}
	
}
