package com.wide.springpos.service;

import java.util.Map;

import com.wide.springpos.dto.ItemDto;
import com.wide.springpos.model.SaleItem;

public interface SaleItemService {
	
	void save(SaleItem saleItem);
	Map<ItemDto, Integer> findItemSoldQuantity();


}
