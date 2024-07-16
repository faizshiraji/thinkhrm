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

import com.hrm.thinkerhouse.entities.Team;
import com.hrm.thinkerhouse.services.TeamService;

@Controller
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	@GetMapping("/teams")
	public String teams(Model model) {
		
		List<Team> teams = teamService.geTeams();
		
		model.addAttribute("teams", teams);
		
		return "admin/teams";
		
	}
	
	@GetMapping("/new_team")
	public String newTeam() {
		return "admin/new_team";
		
	}
	@PostMapping("/add_team")
	public String addTeam(@ModelAttribute("team") Team team, Model model) {
		
		String msgString = "";
		
		try {
			team.setCreateDate(new Date());
			team.setUpdateDate(new Date());
			
			teamService.addTeam(team);
		
			msgString = "New team added successfully.";
		
		} catch (Exception e) {

			msgString = "We encountered some error: " + e.getMessage();
		
		}
		
		List<Team> teams = teamService.geTeams();
		
		model.addAttribute("teams", teams);
		model.addAttribute("msgString", msgString);
		
		return "admin/teams";
		
	}

	@PostMapping("/update_team/{id}")
	public String updateTeam(@PathVariable("id") Integer id, Team team, Model model) {
		
		String msgString = "";
		
		try {
			Team editTeam = teamService.geTeam(id);
			
			editTeam.setTeamName(team.getTeamName());
			editTeam.setStatus(team.getStatus());
			editTeam.setUpdateDate(new Date());
			
			msgString = "Team updated successfully.";
		
		} catch (Exception e) {

			msgString = "We encountered some error: " + e.getMessage();
		
		}
		
		List<Team> geTeams = teamService.geTeams();
		
		model.addAttribute("teams", geTeams);
		model.addAttribute("msgString", msgString);
		
		return "admin/teams";
		
	}
	@GetMapping("/edit_team/{id}")
	public String editTeam(@PathVariable("id") Integer id, Model model) {
		
		Team editTeam = teamService.geTeam(id);
		
		model.addAttribute("team", editTeam);
		
		return "admin/new_team";
		
	}
	@GetMapping("/del_team/{id}")
	public String delTeam(@PathVariable("id") Integer id, Model model) {
		
		String msgString = "";
		
		try {
			teamService.deleteTeam(id);
			
			msgString = "Team deleted succcessfully.";
		} catch (Exception e) {

			msgString = "Getting error to complete this deleteion. Error: " + e.getMessage();
		
		}
		
		List<Team> teams = teamService.geTeams();
		
		model.addAttribute("teams", teams);
		model.addAttribute("msgString", msgString);
		
		return "admin/teams";
		
	}
	
}
