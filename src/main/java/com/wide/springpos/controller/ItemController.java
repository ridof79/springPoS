package com.wide.springpos.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wide.springpos.dto.ItemDto;
import com.wide.springpos.service.ItemService;
import com.wide.springpos.util.GetUserDetail;

@Controller
public class ItemController {
	
    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    
    @RequestMapping("/items")
    public String listItems(Model model) {
        List<ItemDto> items = itemService.findAll();
		model.addAttribute("cashier", GetUserDetail.cashierDto());
        model.addAttribute("items", items);
        return "items/list";
    }
    
    @RequestMapping("/items/new")
    public String showItemForm(Model model) {
        model.addAttribute("item", new ItemDto());
        return "items/new";
    }

    @RequestMapping(value = "/items/new", method = RequestMethod.POST)
    public String saveItem(ItemDto item, Model model) {
    	try {
            itemService.save(item);
            return "redirect:/items";
		} catch (Exception e) {
			model.addAttribute("status", "duplicate");
			return "items/new";
		}
    }

    @RequestMapping("/items/edit/{id}")
    public String showEditItemForm(@PathVariable String id, @RequestParam(value = "status", required = false) String status, Model model) {
    	ItemDto item = itemService.findItemById(id);
        model.addAttribute("item", item);
    	if("duplicate".equals(status)) {
			model.addAttribute("status", "duplicate");
    		return "items/edit";
    	} else {
	        return "items/edit";
    	}
    }

    @RequestMapping(value = "/items/edit", method = RequestMethod.POST)
    public String updateItem(ItemDto item, Model model) {
    	try {
            itemService.update(item);
            return "redirect:/items";
		} catch (Exception e) {
			model.addAttribute("status", "duplicate");
			return "redirect:/items/edit/" + item.getId();
		}
    }

    @RequestMapping("/items/delete/{id}")
    public String deleteItem(@PathVariable String id) {
        itemService.delete(id);
        return "redirect:/items";
    }

}
