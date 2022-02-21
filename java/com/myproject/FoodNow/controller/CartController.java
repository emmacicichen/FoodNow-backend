package com.myproject.FoodNow.controller;

import com.myproject.FoodNow.entity.Cart;
import com.myproject.FoodNow.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CartController {//get customer's cart
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody//convert java obj to JSON, and write in Http response body and return to front end
    public Cart getCart(){
        return cartService.getCart();
    }
}

