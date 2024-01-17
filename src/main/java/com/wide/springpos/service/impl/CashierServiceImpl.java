package com.wide.springpos.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.wide.springpos.dto.CashierDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.wide.springpos.model.Cashier;
import com.wide.springpos.repository.CashierRepository;
import com.wide.springpos.service.CashierService;

@Service
public class CashierServiceImpl implements CashierService {

    private CashierRepository cashierRepository;

    @Autowired
    public CashierServiceImpl(CashierRepository cashierRepository) {
        this.cashierRepository = cashierRepository;
    }

    @Override
    @Transactional
    public void save(Cashier cashier) {
        cashierRepository.save(cashier);
    }

    @Override
    @Transactional
    public void update(Cashier cashier) {
        cashierRepository.update(cashier);
    }

    @Override
    @Transactional
    public void delete(String id) {
        cashierRepository.delete(id);
    }

    @Override
    @Transactional
    public Cashier findCashierById(String id) {
        return cashierRepository.findCashierByID(id);
    }

    @Override
    @Transactional
    public List<CashierDto> findAll() {
        List<CashierDto> cashiers = new ArrayList<>();
        for (Cashier cashier : cashierRepository.findAll()) {
            cashiers.add(new CashierDto(cashier));
        }
        return cashiers;
    }

    @Override
    @Transactional
    public Cashier findCashierByUsername(String username) {
        return cashierRepository.findCashierByUsername(username);
    }

    @Override
    @Transactional
    public Map<CashierDto, Double> findCashierTransaction() {
        Map<CashierDto, Double> cashierTransactionMap = new HashMap<>();

        for (Map.Entry<Cashier, Double> entry : cashierRepository.findCashierTransaction().entrySet()) {
            cashierTransactionMap.put(new CashierDto(entry.getKey()), entry.getValue());
        }
        return cashierTransactionMap;
    }

    @Override
    @Transactional
    public Map<CashierDto, List<Double>> findCashierTransactionList() {
        Map<CashierDto, List<Double>> resultMap = new HashMap<>();

        for (Object[] result : cashierRepository.findCashierTransactionList()) {
            CashierDto cashierDto = new CashierDto((Cashier) result[0]);
            Double totalTransactionPrice = (Double) result[1];

            resultMap.compute(cashierDto, (key, existingList) -> {
                if (existingList == null) {
                    List<Double> newList = new ArrayList<>();
                    newList.add(totalTransactionPrice);
                    return newList;
                } else {
                    existingList.add(totalTransactionPrice);
                    return existingList;
                }
            });
        }

        Map<CashierDto, List<Double>> resultMapNew = new HashMap<>();
        List<CashierDto> cashierList= findAll();
        for (CashierDto cashier : cashierList) {
            List<Double> transaction = new ArrayList<>();
            for (Entry<CashierDto, List<Double>> result : resultMap.entrySet()) {
                if (result.getKey().getId().equals(cashier.getId())) {
                    transaction.add(result.getValue().get(0));
                }
            }
            resultMapNew.put(cashier, transaction);
        }

        return resultMapNew;
    }

}
