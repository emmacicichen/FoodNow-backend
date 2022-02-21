package com.myproject.FoodNow.controller;

import com.myproject.FoodNow.entity.Customer;
import com.myproject.FoodNow.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller//Controller has a @Component inside!, it will be created by Spring
public class SignUpController {

    @Autowired
    private CustomerService customerService;
//@RequestMapping to define REST API
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer) {//@RequestBody can convert HTTP request body to java obj
        customerService.signUp(customer);
    }
}

