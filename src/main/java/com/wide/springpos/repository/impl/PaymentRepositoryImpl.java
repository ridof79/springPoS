package com.wide.springpos.repository.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wide.springpos.model.Payment;
import com.wide.springpos.repository.PaymentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository{

	SessionFactory sessionFactory;
	
    @Autowired
	public PaymentRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Payment payment) {
		sessionFactory.getCurrentSession().save(payment);
	}

	@Override
	public Map<String, Double> paymentAmount() {
		List<Object[]> results = sessionFactory.getCurrentSession().createNativeQuery(
						"SELECT payment_type, SUM(amount) FROM Payment GROUP BY payment_type")
				.getResultList();

		Map<String, Double> paymentAmountMap = new HashMap<>();

		for (Object[] result : results) {
			String paymentType = (String) result[0];
			Double amount = (Double) result[1];
			paymentAmountMap.put(paymentType, amount);
		}

		return paymentAmountMap;
	}
}
