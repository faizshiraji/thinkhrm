package com.hrm.thinkerhouse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.SalaryStructure;

@Repository
public interface SalaryStructureRepo extends JpaRepository<SalaryStructure, Integer> {

	@Query("SELECT s FROM SalaryStructure s WHERE s.employee = :employee")
	List<SalaryStructure> findAllByEmployee(@Param("employee") Employee employee);
	
	
	@Query("SELECT s FROM SalaryStructure s WHERE s.status = :status")
	List<SalaryStructure> findAllByStatus(@Param("status") Integer status);
}
