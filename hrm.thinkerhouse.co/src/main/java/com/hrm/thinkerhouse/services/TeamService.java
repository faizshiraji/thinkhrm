package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.Team;

public interface TeamService {

	public List<Team> geTeams();
	public Team geTeam(int idTeam);
	public Team addTeam(Team team);
	public Team updaTeam(Team team);
	public void deleteTeam(int idTeam);
	public long countTeam();
	
}
