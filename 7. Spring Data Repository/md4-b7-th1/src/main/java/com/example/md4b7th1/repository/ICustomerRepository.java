package com.example.md4b7th1.repository;

import com.example.md4b7th1.model.Customer;
import com.example.md4b7th1.model.Province;
import org.springframework.data.repository.CrudRepository;

public interface ICustomerRepository extends CrudRepository<Customer, Long> {
    Iterable<Customer> findAllByProvince(Province province);

}
