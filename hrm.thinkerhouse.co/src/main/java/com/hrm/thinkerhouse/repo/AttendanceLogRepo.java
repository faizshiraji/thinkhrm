package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.AttendanceLog;

import java.util.Date;
import java.util.List;

@Repository
public interface AttendanceLogRepo extends JpaRepository<AttendanceLog, Integer> {

    AttendanceLog findByInIdAndOutId(int inId, int outId);

    AttendanceLog findByInTimeAndOutTimeAndEmployee_UserId(Date inTime, Date outTime, String userId);

    // Optional: If you want to find all logs for a particular user on a given date range
    List<AttendanceLog> findAllByInTimeBetweenAndEmployee_UserId(Date startDateTime, Date endDateTime, String userId);
}
