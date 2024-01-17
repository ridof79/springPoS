package com.wide.springpos.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CASH")
public class CashPayment extends Payment{
	
	@Column(name = "cash_in_hand", nullable = false)
	private double cashInHand;
	
	public CashPayment(double cashInHand) {
		super();
		this.cashInHand = cashInHand;
	}

	public CashPayment() {
	}
	
	public void setCashInHand(double cashInHand) {
		this.cashInHand = cashInHand;
	}
	
	public double getCashInHand() {
		return cashInHand;
	}

}