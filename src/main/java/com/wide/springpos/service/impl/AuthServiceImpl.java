package com.wide.springpos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.model.Cashier;
import com.wide.springpos.service.AuthService;
import com.wide.springpos.service.CashierService;

@Service
public class AuthServiceImpl implements AuthService{
	
	private CashierService cashierService;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public AuthServiceImpl(CashierService cashierService, PasswordEncoder passwordEncoder) {
		this.cashierService = cashierService;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void register(CashierDto cashierDto) {
		cashierService.save(new Cashier(cashierDto.getUsername(), 
				cashierDto.getName(), 
				passwordEncoder.encode(cashierDto.getPassword())));
	}

}
