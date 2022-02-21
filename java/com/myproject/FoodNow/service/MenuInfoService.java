package com.myproject.FoodNow.service;

import com.myproject.FoodNow.dao.MenuInfoDao;
import com.myproject.FoodNow.entity.MenuItem;
import com.myproject.FoodNow.entity.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//you can also add cache, so you don't need to get data from database, cache can set expiration time
@Service
public class MenuInfoService {

    @Autowired
    private MenuInfoDao menuInfoDao;


    public List<Restaurant> getRestaurants() {
        return menuInfoDao.getRestaurants();
    }

    public List<MenuItem> getAllMenuItem(int restaurantId) {
        return menuInfoDao.getAllMenuItem(restaurantId);
    }

    public MenuItem getMenuItem(int id) {
        return menuInfoDao.getMenuItem(id);
    }
}
