package com.wide.springpos.controller;

import java.util.List;
import java.util.Map;

import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.util.GetUserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wide.springpos.service.CashierService;

@Controller
public class CashierController {

    private CashierService cashierService;

    @Autowired
    public CashierController(CashierService cashierService) {
        this.cashierService = cashierService;
    }

    @RequestMapping("/cashiers")
    public String listCashiers(Model model) {
        List<CashierDto> cashiers = cashierService.findAll();
        Map<CashierDto, List<Double>> cashierTransactionDataList = cashierService.findCashierTransactionList();
        model.addAttribute("cashierTransactionDataList", cashierTransactionDataList);
        model.addAttribute("cashier", GetUserDetail.cashierDto());
        return "cashiers/list";
    }
}
