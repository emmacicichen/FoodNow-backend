package com.myproject.FoodNow.dao;

import com.myproject.FoodNow.entity.MenuItem;
import com.myproject.FoodNow.entity.Restaurant;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MenuInfoDao {

    @Autowired
    private SessionFactory sessionFactory;//inject Hibernate 's session factory

    //1. load all restaurant
    public List<Restaurant> getRestaurants() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createCriteria(Restaurant.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .list();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if(session != null) {
                session.close();
            }
        }

        return new ArrayList<>();
    }

    //2. based on restaurantId, return this restaurant's menu item
    public List<MenuItem> getAllMenuItem(int restaurantId) {
        try (Session session = sessionFactory.openSession()) {
            Restaurant restaurant = session.get(Restaurant.class, restaurantId);//(class, primay Key)
            if (restaurant != null) {//if exist
                return restaurant.getMenuItemList();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    //3. get a specific menuItem based on menuItemId
    public MenuItem getMenuItem(int menuItemId) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(MenuItem.class, menuItemId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

