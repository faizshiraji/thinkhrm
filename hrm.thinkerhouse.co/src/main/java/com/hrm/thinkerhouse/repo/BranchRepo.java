package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Branch;

@Repository
public interface BranchRepo extends JpaRepository<Branch, Integer> {

}
