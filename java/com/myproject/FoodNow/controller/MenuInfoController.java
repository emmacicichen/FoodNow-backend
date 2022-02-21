package com.myproject.FoodNow.controller;

import com.myproject.FoodNow.entity.MenuItem;
import com.myproject.FoodNow.entity.Restaurant;
import com.myproject.FoodNow.service.MenuInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuInfoController {//load restaurants and corresponding menu information
    @Autowired
    private MenuInfoService menuInfoService;

    @RequestMapping(value="/restaurant/{restaurantId}/menu", method = RequestMethod.GET)
    @ResponseBody
    public List<MenuItem> getMenus(@PathVariable("restaurantId") int restaurantId) {//get menu for a specific restaurant
        return menuInfoService.getAllMenuItem(restaurantId);
    }

    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    @ResponseBody
    public List<Restaurant> getRestaurants(){//在页面显示所有餐馆儿
        return menuInfoService.getRestaurants();
    }
}
