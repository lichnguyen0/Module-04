package com.example.md4b11th1.service;

import com.example.md4b11th1.model.Customer;

import java.util.Optional;

public interface ICustomerService {
    Iterable<Customer> findAll();
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    void remove(Long id);
}