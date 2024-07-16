package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.SalaryStructure;

@Repository
public interface SalaryStructureRepo extends JpaRepository<SalaryStructure, Integer> {

}
