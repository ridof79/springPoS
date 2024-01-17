package com.wide.springpos.controller;

import java.util.ArrayList;
import java.util.List;

import com.wide.springpos.exception.PaymentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.dto.ItemDto;
import com.wide.springpos.dto.SaleDto;
import com.wide.springpos.dto.SaleItemDto;
import com.wide.springpos.service.CashierService;
import com.wide.springpos.service.ItemService;
import com.wide.springpos.service.SaleService;
import com.wide.springpos.util.GetUserDetail;

@Controller
@SessionAttributes("saleDto")
public class SaleController {

    private SaleService saleService;
    private ItemService itemService;
    private CashierService cashierService;

    @Autowired
    public SaleController(SaleService saleService, ItemService itemService, CashierService cashierService) {
        this.saleService = saleService;
        this.itemService = itemService;
        this.cashierService = cashierService;
    }

    @ModelAttribute("saleDto")
    public SaleDto saleDto() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new SaleDto(new CashierDto(user.getUsername()));
    }

    @RequestMapping("/sales")
    public String showSale(@RequestParam(value = "status", defaultValue = "false") String status,
                           @ModelAttribute SaleDto saleDto,
                           Model model) {
        model.addAttribute("saleDto", saleDto);
        model.addAttribute("cashier", GetUserDetail.cashierDto());
        if ("insufficientCash".equals(status)) {
            model.addAttribute("status", "insufficientCash");
            return "sales/details";
        }
        return "sales/details";
    }

    @RequestMapping("/sales/add-sale-item")
    public String showItemList(Model model) {
        List<ItemDto> items = itemService.findAll();
        model.addAttribute("items", items);
        return "sales/add-sale-item";
    }

    @RequestMapping(value = "/sales/add-sale-item", method = RequestMethod.POST)
    public String addSaleItem(@ModelAttribute("saleDto") SaleDto saleDto,
                              String itemCode,
                              int quantity) {
        saleDto.addSaleItem(new SaleItemDto(new ItemDto(itemService.findItemByItemCode(itemCode)), quantity));
        return "redirect:/sales";
    }

    @RequestMapping("/sales/delete-sale-item/{id}")
    public String deleteSaleItem(@PathVariable("id") String id, @ModelAttribute("saleDto") SaleDto saleDto) {
        saleDto.deleteSaleItem(id);
        return "redirect:/sales";
    }

    @RequestMapping("/sales/clear-sale-item")
    public String clearSaleItem(@ModelAttribute("saleDto") SaleDto saleDto) {
        saleDto.setSaleItems(new ArrayList<SaleItemDto>());
        return "redirect:/sales";
    }

    @RequestMapping(value = "/sales/submit/qris", method = RequestMethod.POST)
    public String finishSaleQris(@ModelAttribute("saleDto") SaleDto saleDto,
                                 SessionStatus status,
                                 Model model) {
        saleService.saveQrisTransaction(saleDto);
        model.addAttribute("saleId", saleDto.getSaleNumber());
        status.setComplete();
        return "redirect:/sales/receipt";

    }

    @RequestMapping(value = "/sales/submit/cash", method = RequestMethod.POST)
    public String finishSaleCash(@ModelAttribute("saleDto") SaleDto saleDto,
                                 SessionStatus status,
                                 Double cashAmount,
                                 Model model) {
        try {
            saleService.saveCashTransaction(saleDto, cashAmount);
            model.addAttribute("saleId", saleDto.getSaleNumber());
            status.setComplete();
        } catch (PaymentException e) {
            model.addAttribute("status", e.getMessage());
            return "redirect:/sales";
        }
        return "redirect:/sales/receipt";

    }

    @RequestMapping("/sales/history/all")
    public String showAllSale(Model model) {
        List<SaleDto> sales = saleService.findAll();
        model.addAttribute("sales", sales);
        model.addAttribute("cashier", GetUserDetail.cashierDto());
        return "sales/history/all";
    }

    @RequestMapping("/sales/history/{username}")
    public String showSaleByUsername(@PathVariable("username") String username,
                                     Model model) {
        CashierDto cashierDto = GetUserDetail.cashierDto();
        model.addAttribute("cashier", cashierDto);
        if(cashierDto.getUsername().equals(username)) {
            List<SaleDto> sales = saleService.findSaleByCashier(cashierService.findCashierByUsername(username).getId());
            model.addAttribute("sales", sales);
            return "sales/history/cashier";
        }
        model.addAttribute("message", "You cannot view details of transactions for another cashier.");
        return "error/unauthorized";
    }

    @RequestMapping("/sales/receipt")
    public String showReceipt(@RequestParam(value = "saleId", defaultValue = "true") String saleId,
                              Model model) {
        model.addAttribute("sale", new SaleDto(saleService.findBySaleNumber(saleId)));
        model.addAttribute("cashier", GetUserDetail.cashierDto());
        return "sales/receipt";
    }

    @RequestMapping("/sales/detail")
    public String showSaleDetail(@RequestParam(value = "saleId", defaultValue = "true") String saleId,
                                 Model model) {
        CashierDto cashierDto = GetUserDetail.cashierDto();
        SaleDto saleDto = new SaleDto(saleService.findBySaleNumber(saleId));
        model.addAttribute("cashier", cashierDto);
        if(saleDto.getCashier().getId().equals(cashierDto.getId())){
            model.addAttribute("sale", saleDto);
            model.addAttribute("message", "detail");
            return "sales/receipt";
        }
        model.addAttribute("message", "You cannot view details of transactions for another cashier.");
        return "error/unauthorized";
    }
}
 