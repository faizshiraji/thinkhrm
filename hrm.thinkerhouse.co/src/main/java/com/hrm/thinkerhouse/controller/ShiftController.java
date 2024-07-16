package com.hrm.thinkerhouse.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.Shift;
import com.hrm.thinkerhouse.services.EmployeeService;
import com.hrm.thinkerhouse.services.ShiftService;

@Controller
public class ShiftController {

	@Autowired
	private ShiftService shiftService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/all_shift")
	public String allShifts(Model model) {
		
		List<Shift> shifts = shiftService.getShifts();
		
		model.addAttribute("shifts", shifts);
		
		return "admin/shift";
		
	}
	
	@GetMapping("/new_shift")
	public String newShift(Model model) {
		
		model.addAttribute("shift", new Shift());
		
		return "admin/new_shift";
		
	}
	
	@PostMapping("/add_shift")
    public String addShift(@ModelAttribute("shift") Shift shift, Model model) {
        String msgString = "";

        try {
            System.out.println("Here....");
            System.out.println(shift.toString());

            Shift addShift = new Shift();

            addShift.setShiftName(shift.getShiftName());
            addShift.setShiftShortName(shift.getShiftShortName());
            addShift.setDescription(shift.getDescription());
            addShift.setStartTime(formatTime(shift.getStartTime()));
            addShift.setEndTime(formatTime(shift.getEndTime()));
            addShift.setHideEndTime(shift.isHideEndTime());
            addShift.setBreakTime(shift.getBreakTime());
            addShift.setStatus(shift.getStatus());
            addShift.setCreateDate(new Date());
            addShift.setUpdateDate(new Date());
            addShift.setEmployee(null);

            shiftService.addShift(addShift);

            msgString = "New shift added successfully.";
        } catch (Exception e) {
            msgString = "We encountered an error: " + e.getMessage();
        }

        List<Shift> shifts = shiftService.getShifts();
        model.addAttribute("shifts", shifts);
        model.addAttribute("msgString", msgString);

        return "admin/shift";
    }

    private LocalTime formatTime(LocalTime time) {
        return LocalTime.of(time.getHour(), time.getMinute(), time.getSecond());
    }

    @GetMapping("/del_shift/{id}")
    public String delShift(@PathVariable("id") Integer id, Model model) {
    	
    	String msgString = "";
    	
    	try {
			shiftService.deleteShift(id);
			
			msgString = "Your Shift is deleted successfully.";
		} catch (Exception e) {

			msgString = "We have encountered an error: " + e.getMessage();
		}
    	
    	List<Shift> shifts = shiftService.getShifts();
    	
    	model.addAttribute("shifts", shifts);
    	model.addAttribute("msgString", msgString);
    	
    	
		return "admin/shift";
		
	}
    
    @GetMapping("/edit_shift/{id}")
    public String editShift(@PathVariable("id") Integer id, Model model) {
    	
    	Shift shift = shiftService.getShift(id);
    	
    	model.addAttribute("shift", shift);
    	
    	// Add time options to the model
        List<String> timeOptions = Arrays.asList("00:00:00", "01:00:00", "02:00:00", "03:00:00", "04:00:00", "05:00:00", "06:00:00", "07:00:00", "08:00:00", "09:00:00", "10:00:00", "11:00:00", "12:00:00", "13:00:00", "14:00:00", "15:00:00", "16:00:00", "17:00:00", "18:00:00", "19:00:00", "20:00:00", "21:00:00", "22:00:00", "23:00:00");
        model.addAttribute("timeOptions", timeOptions);

        // Add break time options to the model
        List<String> breakTimeOptions = Arrays.asList("00:05:00", "00:10:00", "00:15:00", "00:20:00", "00:25:00", "00:30:00", "00:35:00", "00:40:00", "00:45:00", "00:50:00", "00:55:00", "01:00:00");
        model.addAttribute("breakTimeOptions", breakTimeOptions);

		return "admin/new_shift";
		
	}
    
    @PostMapping("/update_shift/{id}")
    public String addShift(@PathVariable("id") Integer id, @ModelAttribute("shift") Shift shift, Model model) {
        String msgString = "";

        try {

        	Shift editShift = shiftService.getShift(id);
        	
            

            editShift.setShiftName(shift.getShiftName());
            editShift.setShiftShortName(shift.getShiftShortName());
            editShift.setDescription(shift.getDescription());
            editShift.setStartTime(formatTime(shift.getStartTime()));
            editShift.setEndTime(formatTime(shift.getEndTime()));
            editShift.setHideEndTime(shift.isHideEndTime());
            editShift.setBreakTime(shift.getBreakTime());
            editShift.setStatus(shift.getStatus());
            editShift.setUpdateDate(new Date());
            editShift.setEmployee(shift.getEmployee());

            shiftService.updateShift(editShift);

            msgString = "New shift updated successfully.";
        } catch (Exception e) {
            msgString = "We encountered an error: " + e.getMessage();
        }

        List<Shift> shifts = shiftService.getShifts();
        model.addAttribute("shifts", shifts);
        model.addAttribute("msgString", msgString);

        return "admin/shift";
    }
    
    @GetMapping("/shift_employees/{id}")
    public String shiftEmployees(@PathVariable("id") Integer id, Model model) {
    	
    	Shift shift = shiftService.getShift(id);
    	
    	List<Employee> allByShiftIsNull = employeeService.getAllByShiftIsNull();
    	
    	model.addAttribute("shift", shift);
    	model.addAttribute("allByShiftIsNulls", allByShiftIsNull);
    	
		return "admin/shift_employees";
		
	}
    
    @PostMapping("/updateShift")
    public String updateShift(@RequestParam("idShift") int idShift, 
    							@RequestParam("selectedEmployees") List<Integer> selectedEmployees, 
    							Model model) {
        
    	String msgString = "";
    	
    	Shift shift = shiftService.getShift(idShift);

        if (shift != null && selectedEmployees != null) {
            for (Integer employeeId : selectedEmployees) {
            	Employee employee = employeeService.getEmployee(employeeId);
                if (employee != null) {
                    employee.setShift(shift);
                    employeeService.updateEmployee(employee);
                }
            }
            msgString = "Shift Added Successfully.";
        }
        
        List<Shift> shifts = shiftService.getShifts();
        
        model.addAttribute("shifts", shifts);
        model.addAttribute("msgString", msgString);
        
        return "admin/shift"; // Redirect to a relevant page after processing
    }
	
}
