package com.example.md4b6th2.repository;


import com.example.md4b6th2.model.Customer;

public interface ICustomerRepository {
    boolean saveWithStoredProcedure(Customer customer);
}