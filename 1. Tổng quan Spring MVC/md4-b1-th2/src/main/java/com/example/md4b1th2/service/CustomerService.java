package com.example.md4b1th2.service;



import com.example.md4b1th2.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(int id);

}