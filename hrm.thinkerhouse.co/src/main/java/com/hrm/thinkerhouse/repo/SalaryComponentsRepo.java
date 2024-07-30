package com.hrm.thinkerhouse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Employee;
import com.hrm.thinkerhouse.entities.SalaryComponents;

@Repository
public interface SalaryComponentsRepo extends JpaRepository<SalaryComponents, Integer> {

	@Query("SELECT s FROM SalaryComponents s WHERE s.employee = :employee")
	List<SalaryComponents> findAllByEmployee(@Param("employee") Employee employee);
	
	@Query("SELECT s FROM SalaryComponents s WHERE s.status= :status")
	List<SalaryComponents> findAllByStatus(@Param("status") Integer status);
	
}
