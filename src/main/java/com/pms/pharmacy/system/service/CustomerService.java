package com.pms.pharmacy.system.service;


import com.pms.pharmacy.system.model.Company;
import com.pms.pharmacy.system.model.Customer;
import com.pms.pharmacy.system.model.User;
import com.pms.pharmacy.system.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<Customer> getCustomers(){
        return customerRepository.findAllByDeleted(false);
    }

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer){
        Customer existingCustomer = customerRepository.findById(customer.getCustomer_id()).orElse(null);
        existingCustomer.setCustomer_id(customer.getCustomer_id());
        existingCustomer.setCustomer_name(customer.getCustomer_name());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setContact_number(customer.getContact_number());
        existingCustomer.setNic_no(customer.getNic_no());
        return customerRepository.save(existingCustomer);

    }

    public String deleteCustomer(int id){
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isPresent()){
           Customer customer = optionalCustomer.get();
            customer.setDeleted(true);
            customerRepository.save(customer);
            return "Customer removed";
        }

        return "Customer not exsist";
    }


}
