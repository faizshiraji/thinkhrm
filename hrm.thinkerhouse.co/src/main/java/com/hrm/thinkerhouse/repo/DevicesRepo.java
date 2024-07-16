package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Devices;

@Repository
public interface DevicesRepo extends JpaRepository<Devices, Integer> {

}
