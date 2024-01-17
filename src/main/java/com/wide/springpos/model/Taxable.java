package com.wide.springpos.model;

import com.wide.springpos.util.Constant;

public class Taxable {
	private double tax = Constant.TAX_PERCENTAGE;

	public double getTax() {
		return (tax / 100.0);
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	
}
