package com.example.md4b13bt1.service;

import com.example.md4b13bt1.model.Customer;
import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    Customer save(Customer customer);
    Customer findById(Long id);
    void remove(Long id);
}
