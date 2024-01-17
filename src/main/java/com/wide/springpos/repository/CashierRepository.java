package com.wide.springpos.repository;

import java.util.List;
import java.util.Map;
import com.wide.springpos.model.Cashier;

public interface CashierRepository {
	void save(Cashier cashier);
	void update(Cashier cashier);
	void delete(String id);
	Cashier findCashierByID(String id);
	List<Cashier> findAll();
	Cashier findCashierByUsername(String username);
	Map<Cashier, Double> findCashierTransaction();
	List<Object []> findCashierTransactionList();
}
