package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrm.thinkerhouse.entities.DeviceUserInfo;

import java.util.List;

public interface DeviceUserInfoRepo extends JpaRepository<DeviceUserInfo, Integer> {

    @Query("SELECT d FROM DeviceUserInfo d WHERE d.userid = :userid")
    DeviceUserInfo findByUserId(@Param("userid") String userid);

    @Query("SELECT f FROM DeviceUserInfo f WHERE f.status = :status")
    List<DeviceUserInfo> findByStatus(@Param("status") Integer status);
}
