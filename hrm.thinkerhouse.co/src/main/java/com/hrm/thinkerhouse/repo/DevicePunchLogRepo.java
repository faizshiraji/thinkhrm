package com.hrm.thinkerhouse.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.DevicePunchLog;
import com.hrm.thinkerhouse.entities.Devices;

@Repository
public interface DevicePunchLogRepo extends JpaRepository<DevicePunchLog, Integer> {

    @Query("SELECT d FROM DevicePunchLog d WHERE d.recordTime BETWEEN :startDate AND :endDate")
    List<DevicePunchLog> findByRecordTimeBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    
    
    @Query("SELECT d FROM DevicePunchLog d WHERE d.userID = :userID")
    List<DevicePunchLog> findByUserID(@Param("userID") String userID);
    
    @Query("SELECT d FROM DevicePunchLog d WHERE d.userID = :userID AND d.recordTime = :recordTime AND d.devices = :devices")
    List<DevicePunchLog> findByUserIDAndRecordTimeAndDevices(@Param("userID") String userID, 
                                                             @Param("recordTime") Date recordTime, 
                                                             @Param("devices") Devices devices);
    
    @Query("SELECT d FROM DevicePunchLog d WHERE d.logStatus = :logStatus")
    List<DevicePunchLog> findByStatus(@Param("logStatus") Integer logStatus);
    
    @Query("SELECT d FROM DevicePunchLog d WHERE d.devices = :devices")
    List<DevicePunchLog> findByDevice(@Param("devices") Devices devices);
}