package com.wide.springpos.repository;

import java.util.Map;

import com.wide.springpos.model.Item;
import com.wide.springpos.model.SaleItem;

public interface SaleItemRepository {
	
	void save(SaleItem saleItem);
	Map<Item, Integer> findItemSoldQuantity();

}
