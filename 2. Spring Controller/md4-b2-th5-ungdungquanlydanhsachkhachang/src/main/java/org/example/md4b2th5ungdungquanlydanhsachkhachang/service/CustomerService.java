package org.example.md4b2th5ungdungquanlydanhsachkhachang.service;

import org.example.md4b2th5ungdungquanlydanhsachkhachang.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    void save(Customer customer);
}