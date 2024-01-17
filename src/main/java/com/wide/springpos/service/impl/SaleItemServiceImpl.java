package com.wide.springpos.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.wide.springpos.dto.ItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.springpos.model.Item;
import com.wide.springpos.model.SaleItem;
import com.wide.springpos.repository.SaleItemRepository;
import com.wide.springpos.service.SaleItemService;

@Service
public class SaleItemServiceImpl implements SaleItemService{
	
	SaleItemRepository saleItemRepository;

    @Autowired
	public SaleItemServiceImpl(SaleItemRepository saleItemRepository) {
		this.saleItemRepository = saleItemRepository;
	}

	@Override
	@Transactional
	public void save(SaleItem saleItem) {
		saleItemRepository.save(saleItem);
	}

	@Override
	@Transactional
	public Map<ItemDto, Integer> findItemSoldQuantity() {
		Map<ItemDto, Integer> itemSoldMap = new HashMap<>();

		for (Map.Entry<Item, Integer> entry : saleItemRepository.findItemSoldQuantity().entrySet()) {
			itemSoldMap.put(new ItemDto(entry.getKey()), entry.getValue());
		}
		return itemSoldMap;
	}


}
