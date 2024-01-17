package com.wide.springpos.repository.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.wide.springpos.model.Item;
import com.wide.springpos.repository.ItemRepository;

@Repository
public class ItemRepositoryImpl implements ItemRepository{
	
	SessionFactory sessionFactory;

    @Autowired
	public ItemRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public Item findItemById(String id) {
		return sessionFactory.getCurrentSession().find(Item.class, id);
	}

	@Override
	public Item findItemByItemCode(String itemCode) {
		return sessionFactory.getCurrentSession()
	            .createQuery("FROM Item WHERE item_code = :itemCode", Item.class)
	            .setParameter("itemCode", itemCode)
	            .getSingleResult();
	}

	@Override
	public List<Item> findAll() {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Item", Item.class)
				.getResultList();
	}

	@Override
	public void save(Item item) {
		sessionFactory.getCurrentSession().save(item);
	}

	@Override
	public void update(Item item) {
		sessionFactory.getCurrentSession().update(item);
	}

	@Override
	public void delete(String id) {
		Item item = sessionFactory.getCurrentSession().find(Item.class, id);
		if (item != null) {
			sessionFactory.getCurrentSession().delete(item);
		}
	}
	
}
