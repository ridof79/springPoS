package com.wide.springpos.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.service.CashierService;
import com.wide.springpos.service.SaleItemService;

@Component
public class GetUserDetail {
	
	private static CashierService cashierService;

	@Autowired
	public GetUserDetail(SaleItemService saleItemService, CashierService cashierService) {
		GetUserDetail.cashierService = cashierService;
	}
	
	public static CashierDto cashierDto() {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return new CashierDto(cashierService.findCashierByUsername(user.getUsername()));
	}

}
