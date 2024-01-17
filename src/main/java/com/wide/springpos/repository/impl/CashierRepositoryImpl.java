package com.wide.springpos.repository.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.wide.springpos.model.Cashier;
import com.wide.springpos.repository.CashierRepository;

@Repository
public class CashierRepositoryImpl implements CashierRepository {

	SessionFactory sessionFactory;
	
    @Autowired
	public CashierRepositoryImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void save(Cashier cashier) {
		sessionFactory.getCurrentSession().save(cashier);
	}
	
	@Override
	public void update(Cashier cashier) {
		sessionFactory.getCurrentSession().update(cashier);
		
	}

	@Override
	public void delete(String id) {
		Cashier cashier = sessionFactory.getCurrentSession().find(Cashier.class, id);
		if (cashier != null) {
			sessionFactory.getCurrentSession().delete(cashier);
		}
	}

	@Override
	public Cashier findCashierByID(String id) {
		return sessionFactory.getCurrentSession()
				.find(Cashier.class, id);
	}

	@Override
	public List<Cashier> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Cashier", Cashier.class)
				.getResultList();
	}

	@Override
	public Cashier findCashierByUsername(String username) {
	    return sessionFactory.getCurrentSession()
	            .createQuery("FROM Cashier WHERE username = :username", Cashier.class)
	            .setParameter("username", username)
	            .getSingleResult();
	}
	@Override
	public Map<Cashier, Double> findCashierTransaction() {
	    String hql = "SELECT s.cashier, SUM(si.quantity * si.price) " +
	                 "FROM Sale s " +
	                 "JOIN s.saleItems si " +
	                 "GROUP BY s.cashier";

	    List<Object[]> resultList = sessionFactory.getCurrentSession()
	                                              .createQuery(hql, Object[].class)
	                                              .getResultList();

	    return resultList.stream()
	            .collect(Collectors.toMap(
	                    result -> (Cashier) result[0],
	                    result -> (Double) result[1]
	            ));
	}

	@Override
	public List<Object []> findCashierTransactionList() {
		String hql = "SELECT c, SUM(si.price * si.quantity) AS totalTransactionPrice " +
				"FROM Cashier c " +
				"LEFT JOIN Sale s ON c.id = s.cashier.id " +
				"LEFT JOIN SaleItem si ON s.saleNumber = si.sale.saleNumber " +
				"GROUP BY si.id";

		return sessionFactory.getCurrentSession()
				.createQuery(hql, Object[].class)
				.getResultList();
	}
}
