package com.wide.springpos.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wide.springpos.model.Item;
import com.wide.springpos.model.SaleItem;
import com.wide.springpos.repository.SaleItemRepository;

@Repository
public class SaleItemRepositoryImpl implements SaleItemRepository {

    SessionFactory sessionFactory;

    @Autowired
    public SaleItemRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(SaleItem saleItem) {
        sessionFactory.getCurrentSession().save(saleItem);
    }

    @Override
    public Map<Item, Integer> findItemSoldQuantity() {
        Session session = sessionFactory.getCurrentSession();

        String hql = "SELECT si.item, SUM(si.quantity) FROM SaleItem si GROUP BY si.item";
        List<Object[]> resultList = session.createQuery(hql, Object[].class).getResultList();

        return resultList.stream()
                .collect(Collectors.toMap(
                        result -> (Item) result[0],
                        result -> ((Number) result[1]).intValue()
                ));
    }
}
