package com.wide.springpos.repository;

import java.util.List;

import com.wide.springpos.model.Sale;

public interface SaleRepository {
	void save(Sale sale);
	void delete(String saleNumber);
	Sale findBySaleNumber(String saleNumber);
	List<Sale> findAll();
	List<Sale> findSaleByCashier(String cashierId);
}
