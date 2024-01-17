package com.wide.springpos.dto;

import com.fasterxml.uuid.Generators;
import com.wide.springpos.model.SaleItem;

public class SaleItemDto {
	
	private String id;
	private ItemDto item;
	private double price;
	private int quantity;
	
	public SaleItemDto(SaleItem saleItem) {
		this.id = saleItem.getId();
		this.item = new ItemDto(saleItem.getItem());
		this.price = saleItem.getPrice();
		this.quantity = saleItem.getQuantity();
	}
	
	public SaleItemDto(ItemDto item, int quantity) {
		this.id = Generators.timeBasedGenerator().generate().toString();
		this.item = item;
		this.price = item.getPrice();
		this.quantity = quantity;
	}
	
	public SaleItemDto() {
		this.id = Generators.timeBasedGenerator().generate().toString();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ItemDto getItem() {
		return item;
	}

	public void setItem(ItemDto itemDto) {
		this.item = itemDto;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double totalPrice() {
		return this.quantity * this.price;
	}

	
}
