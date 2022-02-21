package com.myproject.FoodNow.service;

import com.myproject.FoodNow.dao.CustomerDao;
import com.myproject.FoodNow.entity.Cart;
import com.myproject.FoodNow.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired//Spring created the CustomerDao obj and inject here(CustomerService)
    private CustomerDao customerDao;

    public void signUp(Customer customer) {
        Cart cart = new Cart();//when sign up, assign a cart to the customer
        customer.setCart(cart);

        customer.setEnabled(true);
        customerDao.signUp(customer);//call Dao layer, to write in database
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}
