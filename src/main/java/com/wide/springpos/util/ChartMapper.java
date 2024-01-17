package com.wide.springpos.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.wide.springpos.dto.CashierDto;
import com.wide.springpos.dto.ItemDto;

public class ChartMapper {

	public static String mapChartItem(Map<ItemDto, Integer> chartMap) {
		Gson gsonObj = new Gson();

		double total = 0;
		for (Integer value : chartMap.values()) {
			total += value;
		}
		
		List<Map<String, Object>> list = new ArrayList<>();
		for (Entry<ItemDto, Integer> entry : chartMap.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("label", entry.getKey().getDescription());
            double percentage = Double.parseDouble(new DecimalFormat("#.##").format((entry.getValue() / total) * 100));
            map.put("y", percentage);
			list.add(map);
		}

		return gsonObj.toJson(list);
	}
	
	public static String mapChartCashier(Map<CashierDto, Double> chartMap) {
		Gson gsonObj = new Gson();

		double total = 0;
		for (Double value : chartMap.values()) {
			total += value;
		}
		
		List<Map<String, Object>> list = new ArrayList<>();
		for (Entry<CashierDto, Double> entry : chartMap.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("label", entry.getKey().getName());
            double percentage = Double.parseDouble(new DecimalFormat("#.##").format((entry.getValue() / total) * 100));
            map.put("y", percentage);
			list.add(map);
		}

		return gsonObj.toJson(list);
	}

	public static String mapChartPayment(Map<String, Double> paymentAmount) {
		Gson gsonObj = new Gson();

		List<Map<String, Object>> list = new ArrayList<>();
		for (Entry<String, Double> entry : paymentAmount.entrySet()) {
			Map<String, Object> map = new HashMap<>();
			map.put("label", entry.getKey());
			map.put("y", entry.getValue());
			list.add(map);
		}

		return gsonObj.toJson(list);
	}
}
