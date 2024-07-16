package com.hrm.thinkerhouse.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.thinkerhouse.entities.PaymentHistory;
import com.hrm.thinkerhouse.repo.PaymentHistoryRepo;
import com.hrm.thinkerhouse.services.PaymentHistoryService;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

	@Autowired
	public PaymentHistoryRepo paymentHistoryRepo;
	
	@Override
	public List<PaymentHistory> getPaymentHistories() {
		return paymentHistoryRepo.findAll();
	}

	@Override
	public PaymentHistory getPaymentHistory(int idPaymentHistory) {
		return paymentHistoryRepo.findById(idPaymentHistory).get();
	}

	@Override
	public PaymentHistory addPaymentHistory(PaymentHistory paymentHistory) {
		return paymentHistoryRepo.save(paymentHistory);
	}

	@Override
	public PaymentHistory updatePaymentHistory(PaymentHistory paymentHistory) {
		return paymentHistoryRepo.save(paymentHistory);
	}

	@Override
	public void deletePaymentHistory(int idPaymentHistory) {
		paymentHistoryRepo.deleteById(idPaymentHistory);
	}

	@Override
	public long countPaymentHistory() {
		return paymentHistoryRepo.count();
	}

}
