package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Integer> {

}
