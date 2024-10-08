package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Deductions;

@Repository
public interface DeductionsRepo extends JpaRepository<Deductions, Integer> {

}
