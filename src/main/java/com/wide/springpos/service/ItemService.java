package com.wide.springpos.service;

import java.util.List;

import com.wide.springpos.dto.ItemDto;
import com.wide.springpos.model.Item;

public interface ItemService {

	ItemDto findItemById(String id);
	Item findItemByItemCode(String itemCode);
	List<ItemDto> findAll();
	void save(ItemDto itemDto);
	void update(ItemDto item);
	void delete(String id);
	
}
