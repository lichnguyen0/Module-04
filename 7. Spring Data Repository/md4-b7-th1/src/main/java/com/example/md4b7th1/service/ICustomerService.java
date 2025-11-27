package com.example.md4b7th1.service;

import com.example.md4b7th1.model.Customer;
import com.example.md4b7th1.model.Province;

public interface ICustomerService extends IGenerateService<Customer> {
    Iterable<Customer> findAllByProvince(Province province);
}