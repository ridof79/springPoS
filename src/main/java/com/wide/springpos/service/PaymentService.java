package com.wide.springpos.service;

import com.wide.springpos.model.Payment;

import java.util.Map;

public interface PaymentService {
	
	void save(Payment payment);
	Map<String, Double> paymentAmount();

}
