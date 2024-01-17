package com.wide.springpos.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.wide.springpos.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.springpos.dto.SaleDto;
import com.wide.springpos.dto.SaleItemDto;
import com.wide.springpos.model.CashPayment;
import com.wide.springpos.model.QrisPayment;
import com.wide.springpos.model.Payment;
import com.wide.springpos.model.Sale;
import com.wide.springpos.model.SaleItem;
import com.wide.springpos.repository.SaleRepository;
import com.wide.springpos.service.CashierService;
import com.wide.springpos.service.ItemService;
import com.wide.springpos.service.PaymentService;
import com.wide.springpos.service.SaleItemService;
import com.wide.springpos.service.SaleService;

@Service
public class SaleServiceImpl implements SaleService {

	private SaleRepository saleRepository;
	private PaymentService paymentService;
	private SaleItemService saleItemService;
	private CashierService cashierService;
	private ItemService itemService;

	@Autowired
	public SaleServiceImpl(SaleRepository saleRepository, PaymentService paymentService,
			SaleItemService saleItemService, CashierService cashierService, ItemService itemService) {
		this.saleRepository = saleRepository;
		this.paymentService = paymentService;
		this.saleItemService = saleItemService;
		this.cashierService = cashierService;
		this.itemService = itemService;
	}

	@Override
	@Transactional
	public void saveQrisTransaction(SaleDto saleDto) {
		Sale sale = convertSale(saleDto);
		Payment payment = new QrisPayment();
		setupPaymentAndSave(sale, saleDto, payment);
	}

	@Override
	@Transactional
	public void saveCashTransaction(SaleDto saleDto, Double cashInHand) throws PaymentException {
		if(cashInHand > saleDto.totalPrice()) {
			Sale sale = convertSale(saleDto);
			Payment payment = new CashPayment(cashInHand);
			setupPaymentAndSave(sale, saleDto, payment);
		} else {
			throw new PaymentException("insufficientCash");
		}
	}

	@Override
	@Transactional
	public void delete(String saleNumber) {
		saleRepository.delete(saleNumber);
	}

	@Override
	@Transactional
	public Sale findBySaleNumber(String saleNumber) {
		return saleRepository.findBySaleNumber(saleNumber);
	}

	@Override
	@Transactional
	public List<SaleDto> findAll() {
		return saleRepository.findAll().stream().map(SaleDto::new).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public List<SaleDto> findSaleByCashier(String cashierId) {
		return saleRepository.findSaleByCashier(cashierId).stream().map(SaleDto::new).collect(Collectors.toList());
	}

	private SaleItem convertSaleItem(SaleItemDto saleItemDto) {
		SaleItem saleItem = new SaleItem();
		saleItem.setId(saleItemDto.getId());
		saleItem.setItem(itemService.findItemByItemCode(saleItemDto.getItem().getItemCode()));
		saleItem.setPrice(saleItemDto.getPrice());
		saleItem.setQuantity(saleItemDto.getQuantity());
		return saleItem;
	}
	
	private Sale convertSale(SaleDto saleDto) {
		Sale sale = new Sale();
		sale.setSaleNumber(saleDto.getSaleNumber());
		sale.setTransactionDate(new Date());
		sale.setCashier(cashierService.findCashierByUsername(saleDto.getCashier().getUsername()));

		List<SaleItem> saleItems = saleDto.getSaleItems().stream().map(this::convertSaleItem)
				.peek(saleItem -> saleItem.setSale(sale)).collect(Collectors.toList());

		sale.setSaleItems(saleItems);
		return sale;
	}
	
	private void setupPaymentAndSave(Sale sale, SaleDto saleDto, Payment payment) {
		payment.setSale(sale);
		payment.setAmount(saleDto.totalPrice());
		payment.validate();
		sale.setPayment(payment);
		saleRepository.save(sale);
		paymentService.save(payment);
		sale.getSaleItems().forEach(saleItemService::save);
	}

}
