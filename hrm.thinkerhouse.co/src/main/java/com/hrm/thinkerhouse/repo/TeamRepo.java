package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team, Integer> {

}
