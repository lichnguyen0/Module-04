package com.example.md4b6th2.service;


import com.example.md4b6th2.model.Customer;

public interface ICustomerService {
    boolean saveWithStoredProcedure(Customer customer);
}
