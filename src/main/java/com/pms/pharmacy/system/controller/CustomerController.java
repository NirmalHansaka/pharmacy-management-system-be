package com.pms.pharmacy.system.controller;


import com.pms.pharmacy.system.model.Customer;
import com.pms.pharmacy.system.model.Medicine;
import com.pms.pharmacy.system.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/getCustomer")
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @PostMapping("/addCustomer")
    public Customer addCustomer(@RequestBody Customer customer){
        return  customerService.saveCustomer(customer);
    }

    @PutMapping("/updateCustomer")
    public Customer updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer")
    public String deleteCustomer(@RequestBody Integer id){
        return customerService.deleteCustomer(id);
    }
}
