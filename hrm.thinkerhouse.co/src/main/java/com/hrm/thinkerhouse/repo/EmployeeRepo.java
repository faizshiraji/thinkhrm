package com.hrm.thinkerhouse.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.hrm.thinkerhouse.entities.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Integer> {
    
    Employee findByEmail(String email);
    
    Employee findByPhone(String phone);
    
    // Adjusted to match field in Employee entity
    @Query("SELECT e FROM Employee e WHERE e.userId = :userid") // Use the correct case as per the Employee entity
    Employee findByUserId(@Param("userid") String userid);
    
    @Query("SELECT e FROM Employee e WHERE e.shift IS NULL")
    List<Employee> findAllByShiftIsNull();
}
