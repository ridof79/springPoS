package com.wide.springpos.service.impl;

import com.wide.springpos.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wide.springpos.model.Payment;
import com.wide.springpos.service.PaymentService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    @Transactional
    public Map<String, Double> paymentAmount() {
        return paymentRepository.paymentAmount();
    }

}
