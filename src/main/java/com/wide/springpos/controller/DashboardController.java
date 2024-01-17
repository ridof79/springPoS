package com.wide.springpos.controller;

import com.wide.springpos.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.wide.springpos.service.CashierService;
import com.wide.springpos.service.SaleItemService;
import com.wide.springpos.util.ChartGenerator;
import com.wide.springpos.util.GetUserDetail;

@Controller
public class DashboardController {

	private SaleItemService saleItemService;
	private CashierService cashierService;
	private PaymentService paymentService;

	@Autowired
	public DashboardController(SaleItemService saleItemService, CashierService cashierService, PaymentService paymentService) {
		this.saleItemService = saleItemService;
		this.cashierService = cashierService;
		this.paymentService = paymentService;
	}

	@RequestMapping("/home")
    public String listSales(Model model) {
		String itemChart = ChartGenerator.generateChartItem(saleItemService.findItemSoldQuantity());
		String cashierChart = ChartGenerator.generateChartCashier(cashierService.findCashierTransaction());
		String paymentAmountChart = ChartGenerator.generateChartPayment(paymentService.paymentAmount());
		model.addAttribute("itemChart", itemChart);
		model.addAttribute("cashierChart", cashierChart);
		model.addAttribute("paymentChart", paymentAmountChart);
		model.addAttribute("cashier", GetUserDetail.cashierDto());
        return "dashboard";
    }
}
