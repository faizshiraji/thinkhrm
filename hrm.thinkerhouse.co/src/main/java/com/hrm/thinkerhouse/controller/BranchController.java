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

import com.hrm.thinkerhouse.entities.Branch;
import com.hrm.thinkerhouse.services.BranchService;

import jakarta.persistence.Id;

@Controller
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	@GetMapping("/branch")
	public String branch(Model model) {
		
		List<Branch> branchs = branchService.getBranchs();
		
		model.addAttribute("branchs", branchs);
		
		return "admin/branchs";
		
	}
	
	@GetMapping("/new_branch")
	public String newBranch() {
		return "admin/new_branch";
		
	}
	
	@PostMapping("/add_branch")
	public String addBranch(@ModelAttribute("branch") Branch branch, Model model) {
		
		String msgString = "";
		
		try {
			branch.setCreateDate(new Date());
			branch.setUpdateDate(new Date());
			
			branchService.addBranch(branch);
			
			msgString = "Branch added successfully.";
		} catch (Exception e) {

			msgString = "We encountered some error: " + e.getMessage();
		}
		
		List<Branch> branchs = branchService.getBranchs();
		
		model.addAttribute("branchs", branchs);
		model.addAttribute("msgString", msgString);
		
		return "admin/branchs";
		
	}

	@PostMapping("/update_branch/{id}")
	public String updateBranch(@PathVariable("id")Integer id, Branch branch, Model model) {
		
		String msgString = "";
		
		try {
			Branch editBranch = branchService.getBranch(id);
			
			editBranch.setBranchName(branch.getBranchName());
			editBranch.setBranchAddress(branch.getBranchAddress());
			editBranch.setLocation(branch.getLocation());
			editBranch.setStatus(branch.getStatus());
			editBranch.setUpdateDate(new Date());
			
			branchService.updateBranch(editBranch);
			
			msgString = "Branch updated successfully.";
		
		} catch (Exception e) {

			msgString = "We encountered some error: " + e.getMessage();
			
		}
	
		List<Branch> branchs = branchService.getBranchs();
		
		model.addAttribute("branchs", branchs);
		model.addAttribute("msgString", msgString);
		
		return "admin/branchs";
		
	}

	@GetMapping("/del_branch/{id}")
	public String delBranch(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			branchService.deleteBranch(id);
			
			msgString = "Branch deleted successfully.";
		} catch (Exception e) {

			msgString = "We encountered error: " + e.getMessage();
			
		}
		
		List<Branch> branchs = branchService.getBranchs();
		
		model.addAttribute("branchs", branchs);
		model.addAttribute("msgString", msgString);
		
		
		return "admin/branchs";
		
	}
	
	@GetMapping("/edit_branch/{id}")
	public String editBranch(@PathVariable("id") Integer id, Model model) {
		
		Branch editBranch = branchService.getBranch(id);
		
		model.addAttribute("branch", editBranch);
		
		return "admin/new_branch";
		
	}
	
}
