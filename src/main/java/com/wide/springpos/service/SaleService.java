package com.wide.springpos.service;

import java.util.List;

import com.wide.springpos.dto.SaleDto;
import com.wide.springpos.exception.PaymentException;
import com.wide.springpos.model.Sale;

public interface SaleService {
	void saveQrisTransaction(SaleDto sale);
	void saveCashTransaction(SaleDto sale, Double cashInHand) throws PaymentException;
	void delete(String saleNumber);
	Sale findBySaleNumber(String saleNumber);
	List<SaleDto> findAll();
	List<SaleDto> findSaleByCashier(String cashierId);

}
