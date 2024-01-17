package com.wide.springpos.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("QRIS")
public class QrisPayment extends Payment{
	
	public QrisPayment() {
	}
	
}