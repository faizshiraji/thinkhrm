package com.hrm.thinkerhouse.services;

import java.util.List;

import com.hrm.thinkerhouse.entities.PaymentHistory;

public interface PaymentHistoryService {

	public List<PaymentHistory> getPaymentHistories();
	public PaymentHistory getPaymentHistory(int idPaymentHistory);
	public PaymentHistory addPaymentHistory(PaymentHistory paymentHistory);
	public PaymentHistory updatePaymentHistory(PaymentHistory paymentHistory);
	public void deletePaymentHistory(int idPaymentHistory);
	public long countPaymentHistory();
	
}
