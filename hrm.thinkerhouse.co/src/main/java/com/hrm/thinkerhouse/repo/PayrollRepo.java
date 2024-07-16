package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Payroll;

@Repository
public interface PayrollRepo extends JpaRepository<Payroll, Integer> {

}
