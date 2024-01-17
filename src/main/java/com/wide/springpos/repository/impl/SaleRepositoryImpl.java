package com.wide.springpos.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wide.springpos.model.Sale;
import com.wide.springpos.repository.SaleRepository;

@Repository
public class SaleRepositoryImpl implements SaleRepository {

    SessionFactory sessionFactory;

    @Autowired
    public SaleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Sale sale) {
        sessionFactory.getCurrentSession()
                .save(sale);
    }

    @Override
    public void delete(String saleNumber) {
        Sale sale = sessionFactory.getCurrentSession()
                .find(Sale.class, saleNumber);
        if (sale != null) {
            sessionFactory.getCurrentSession()
                    .delete(sale);
        }
    }

    @Override
    public Sale findBySaleNumber(String saleNumber) {
        return sessionFactory.getCurrentSession()
                .find(Sale.class, saleNumber);
    }

    @Override
    public List<Sale> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Sale", Sale.class)
                .getResultList();
    }

    @Override
    public List<Sale> findSaleByCashier(String cashierId) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Sale s WHERE s.cashier.id = :cashierId", Sale.class)
                .setParameter("cashierId", cashierId)
                .getResultList();
    }
}
