package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.Team;
import com.hrm.thinkerhouse.repo.TeamRepo;
import com.hrm.thinkerhouse.services.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	public TeamRepo teamRepo;
	
	@Override
	public List<Team> geTeams() {
		return teamRepo.findAll();
	}

	@Override
	public Team geTeam(int idTeam) {
		return teamRepo.findById(idTeam).get();
	}

	@Override
	public Team addTeam(Team team) {
		return teamRepo.save(team);
	}

	@Override
	public Team updaTeam(Team team) {
		return teamRepo.save(team);
	}

	@Override
	public void deleteTeam(int idTeam) {
		teamRepo.deleteById(idTeam);
	}

	@Override
	public long countTeam() {
		return teamRepo.count();
	}

}
