package com.example.md4b9th1.service;



import com.example.md4b9th1.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    List<Customer> findAll() throws Exception;

    Customer findOne(Long id) throws Exception;
}