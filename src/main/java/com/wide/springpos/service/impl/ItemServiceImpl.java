package com.wide.springpos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wide.springpos.dto.ItemDto;
import com.wide.springpos.model.Item;
import com.wide.springpos.repository.ItemRepository;
import com.wide.springpos.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

	private ItemRepository itemRepository;
    
	@Autowired
	public ItemServiceImpl(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@Override
	@Transactional
	public ItemDto findItemById(String id) {
		return new ItemDto(itemRepository.findItemById(id));
	}

	@Override
	@Transactional
	public Item findItemByItemCode(String itemCode) {
		return itemRepository.findItemByItemCode(itemCode);
	}

	@Override
	@Transactional
	public List<ItemDto> findAll() {
		List<Item> items = itemRepository.findAll();
		List<ItemDto> itemsDto = new ArrayList<>();
		for(Item item : items) {
			itemsDto.add(new ItemDto(item));
		}
		return itemsDto;
	}

	@Override
	@Transactional
	public void save(ItemDto itemDto) {
		Item item = convertItem(itemDto);
		itemRepository.save(item);		
	}

	@Override
	@Transactional
	public void update(ItemDto itemDto) {
		itemRepository.update(convertItem(itemDto));
	}

	@Override
	@Transactional
	public void delete(String id) {
		itemRepository.delete(id);
	}

	private Item convertItem(ItemDto itemDto) {
		return new Item(itemDto.getId(), 
				itemDto.getItemCode(), 
				itemDto.getPrice(), 
				itemDto.getDescription(), 
				itemDto.getType(), 
				itemDto.isTaxable());
	}
}
