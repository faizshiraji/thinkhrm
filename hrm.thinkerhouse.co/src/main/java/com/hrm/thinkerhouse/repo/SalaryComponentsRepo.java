package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.SalaryComponents;

@Repository
public interface SalaryComponentsRepo extends JpaRepository<SalaryComponents, Integer> {

}
