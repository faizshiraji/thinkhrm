package com.hrm.thinkerhouse.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.AttendanceLog;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceLogRepo extends JpaRepository<AttendanceLog, Integer> {

    AttendanceLog findByInIdAndOutId(int inId, int outId);

    AttendanceLog findByInTimeAndOutTimeAndEmployee_UserId(Date inTime, Date outTime, String userId);

    // Find all logs between two dates
    List<AttendanceLog> findAllByInTimeBetween(Date startDateTime, Date endDateTime);

    @Query("SELECT a FROM AttendanceLog a WHERE a.inTime BETWEEN :startDateTime AND :endDateTime")
    Page<AttendanceLog> findAllByInTimeBetween(Date startDateTime, Date endDateTime, Pageable pageable);
    
    @Query("SELECT a FROM AttendanceLog a WHERE a.employee.id = :userId AND MONTH(a.inTime) = :month AND YEAR(a.inTime) = :year")
    List<AttendanceLog> findByEmployeeAndMonth(int userId, int month, int year);
}