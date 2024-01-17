package com.wide.springpos.repository;

import java.util.List;

import com.wide.springpos.model.Item;

public interface ItemRepository {
	Item findItemById(String id);
	Item findItemByItemCode(String itemCode);
	List<Item> findAll();
	void save(Item item);
	void update(Item item);
	void delete(String id);
	
}