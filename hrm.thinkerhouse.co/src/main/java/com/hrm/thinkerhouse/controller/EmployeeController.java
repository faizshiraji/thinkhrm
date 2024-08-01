package com.hrm.thinkerhouse.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hrm.thinkerhouse.entities.Branch;
import com.hrm.thinkerhouse.entities.Department;
import com.hrm.thinkerhouse.entities.DeviceUserInfo;
import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.Team;
import com.hrm.thinkerhouse.services.BranchService;
import com.hrm.thinkerhouse.services.DepartpentServices;
import com.hrm.thinkerhouse.services.DeviceUserInfoService;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.FileUploadService;
import com.hrm.thinkerhouse.services.TeamService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TeamService teamService;
	
	@Autowired
	private DepartpentServices departpentServices;
	
	@Autowired
	private BranchService branchService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private DeviceUserInfoService deviceUserInfoService;
	
	@GetMapping("/all_employee")
    public String allEmployee(Model model) {
        List<Employee> employees = employeeService.getEmployees();
        if (employees == null) {
            System.out.println("Employee list is null");
        } else {
            System.out.println("Employee list size: " + employees.size());
        }
        model.addAttribute("employees", employees);
        return "admin/allemployees";
    }
	
	@GetMapping("/new_employee")
    public String newEmployee(Model model) {
        List<Department> departments = departpentServices.getDepartments();
        List<Branch> branches = branchService.getBranchs();
        List<Team> teams = teamService.geTeams();

        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", departments);
        model.addAttribute("branches", branches);
        model.addAttribute("teams", teams);

        return "admin/new_employee";
    }

	@PostMapping("/add_employee")
	public String addEmployee(@ModelAttribute("employee") Employee employee, 
	                          BindingResult result, 
	                          final @RequestParam(value = "image") MultipartFile imageFile,
	                          Model model) {
		
		String msgString = "";
	    if (result.hasErrors()) {
	    	
	    	msgString = "We encountered an error: " + result.toString();
	    }
	    
	    String imageString = fileUploadService.save(imageFile);
	    
	    Employee addingEmployee = new Employee();
	    
	    addingEmployee.setFirstName(employee.getFirstName());
	    addingEmployee.setLastName(employee.getLastName());
	    addingEmployee.setEmail(employee.getEmail());
	    addingEmployee.setPhone(employee.getPhone());
	    addingEmployee.setPosition(employee.getPosition());
	    addingEmployee.setDate(employee.getDate());
	    addingEmployee.setStatus(employee.getStatus());
	    addingEmployee.setCreateDate(new Date());
	    addingEmployee.setUpdateDate(new Date());
	    addingEmployee.setUserId(null);
	    addingEmployee.setUsers(null);
	    addingEmployee.setImage(imageString);
	    addingEmployee.setTeam(employee.getTeam());
	    addingEmployee.setBranch(employee.getBranch());
	    addingEmployee.setDepartment(employee.getDepartment());
		
	    employeeService.addEmployee(addingEmployee);
	    
	    msgString = "New employee added successfully.";
	    
	    List<Employee> employees = employeeService.getEmployees();
	    model.addAttribute("msgString", msgString);
	    model.addAttribute("employees", employees);

	    return "admin/allemployees"; 
	}
	
	@PostMapping("/update_employee/{id}")
	public String updateEmoloyee(
								@PathVariable("id") Integer id, 
								@ModelAttribute("employee") Employee employee, 
								BindingResult result,
								final @RequestParam(value = "image") MultipartFile imageFile,
								Model model) {
		
		Employee editEmployee = employeeService.getEmployee(id);
		
		String msgString = "";
		
		try {
			if (result.hasErrors()) {
				
				msgString = "We encountered as error: " + result.toString();
			}
			
			String save = editEmployee.getImage();
			
			if (imageFile != null && !imageFile.isEmpty()) {
				save = fileUploadService.save(imageFile);
			}
			
			editEmployee.setFirstName(employee.getFirstName());
			editEmployee.setLastName(employee.getLastName());
			editEmployee.setEmail(employee.getEmail());
			editEmployee.setPhone(employee.getPhone());
			editEmployee.setPosition(employee.getPosition());
			editEmployee.setDate(employee.getDate());
			editEmployee.setStatus(employee.getStatus());
			editEmployee.setUpdateDate(new Date());
			editEmployee.setUserId(employee.getUserId());
			editEmployee.setUsers(employee.getUsers());
			editEmployee.setImage(save);
			editEmployee.setTeam(employee.getTeam());
			editEmployee.setBranch(employee.getBranch());
			editEmployee.setDepartment(employee.getDepartment());
			
			employeeService.updateEmployee(editEmployee);
			
			msgString = "Employee updated successfully.";
		} catch (Exception e) {

			msgString = "We encountered an error: " + e.getMessage();
		
		}
	    
	    List<Employee> employees = employeeService.getEmployees();
	    model.addAttribute("employees", employees);
	    model.addAttribute("msgString", msgString);
		
		
		return "admin/allemployees";
		
	}
	
	@GetMapping("/del_employee/{id}")
	public String delEmployee(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			employeeService.deleteEmployee(id);
			
			msgString = "Employee deleted successfully.";
		} catch (Exception e) {

			msgString = "Getting error to complete this deletion " + e.getMessage();
		
		}
		
		List<Employee> employees = employeeService.getEmployees();
		
		model.addAttribute("employees", employees);
		model.addAttribute("msgString", msgString);
		
		
		return "admin/allemployees";
		
	}
	
	@GetMapping("/read_employee/{id}")
	public String readEmployee(@PathVariable("id") Integer id, Model model) {
		
		Employee employee = employeeService.getEmployee(id);
		
		model.addAttribute("employee", employee);
		
		return "admin/read_employee";
		
	}
	
	@GetMapping("/edit_employee/{id}")
	public String editEmployee(@PathVariable("id") Integer id, Model model) {
		
		Employee employee = employeeService.getEmployee(id);
		
		String code = "1";
		
		model.addAttribute("code",code);
		model.addAttribute("employee", employee);
		
		return "admin/new_employee";
		
	}
	
	@GetMapping("/map_device/{id}")
	public String mapDevice(@PathVariable("id") Integer id,
							Model model) {
		
		Employee employee = employeeService.getEmployee(id);
		
		List<DeviceUserInfo> deviceUserInfoByStatus = deviceUserInfoService.getDeviceUserInfoByStatus(3);
		
		model.addAttribute("deviceUserInfoByStatus", deviceUserInfoByStatus);
		model.addAttribute("employee", employee);
		return "admin/map_device";
	}
	
	@PostMapping("/map_employee/{id}")
    public String mapEmployee(@PathVariable("id") Integer id,
                              @ModelAttribute("employee") Employee employee,
                              Model model) {

        String msgString = "";

        Employee mapEmployee = employeeService.getEmployee(id);

        try {
            // Log the original userId for debugging
            System.out.println("Original userId: " + employee.getUserId());

            // Handle the null value for userId
            if (employee.getUserId() == null || employee.getUserId().isEmpty()) {
                mapEmployee.setUserId(null);
            } else {
                String userId = sanitizeInput(employee.getUserId().trim());
                System.out.println("Sanitized userId: " + userId);
                try {
                    int userIdInt = Integer.parseInt(userId);
                    mapEmployee.setUserId(String.valueOf(userIdInt));
                } catch (NumberFormatException e) {
                    msgString = "Invalid user id format: " + userId;
                    model.addAttribute("msgString", msgString);
                    return "admin/allemployees";
                }
            }

            employeeService.updateEmployee(mapEmployee);
            msgString = "Employee mapped successfully.";

        } catch (Exception e) {
            msgString = "We encountered an error: " + e.getMessage();
        }

        List<Employee> employees = employeeService.getEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("msgString", msgString);

        return "admin/allemployees";
    }

    // Method to sanitize input
    private String sanitizeInput(String input) {
        // Remove any non-ASCII characters
        return input.replaceAll("[^\\p{Print}]", "");
    }



	
}
