package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
