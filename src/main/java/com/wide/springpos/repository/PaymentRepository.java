package com.wide.springpos.repository;

import com.wide.springpos.model.Payment;

import java.util.Map;

public interface PaymentRepository {
	void save(Payment payment);
	Map<String, Double> paymentAmount();

}
