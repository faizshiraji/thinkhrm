package com.hrm.thinkerhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentHistoryController {

	@GetMapping("/paymenthistory")
	public String viewPaymentHistory() {
		return "admin/paymenthistory";
		
	}
	
}
