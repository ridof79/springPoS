package com.wide.springpos.service;

import java.util.List;
import java.util.Map;

import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.model.Cashier;

public interface CashierService {
	
	void save(Cashier cashier);
	void update(Cashier cashier);
	void delete(String id);
	Cashier findCashierById(String id);
	List<CashierDto> findAll();
	Cashier findCashierByUsername(String username);
	Map<CashierDto, Double> findCashierTransaction();
	Map<CashierDto, List<Double>> findCashierTransactionList();
}
