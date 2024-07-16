package com.hrm.thinkerhouse.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.thinkerhouse.entities.PaymentHistory;

@Repository
public interface PaymentHistoryRepo extends JpaRepository<PaymentHistory, Integer> {

}
