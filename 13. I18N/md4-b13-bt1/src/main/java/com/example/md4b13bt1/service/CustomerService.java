package com.example.md4b13bt1.service;

import com.example.md4b13bt1.model.Customer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class CustomerService implements ICustomerService {

    private final List<Customer> customers = Collections.synchronizedList(new ArrayList<>());
    private final AtomicLong idCounter = new AtomicLong();

    public CustomerService() {
        customers.add(new Customer(1L, "John Doe", "john@example.com"));
        customers.add(new Customer(2L, "Jane Smith", "jane@example.com"));
        idCounter.set(3L);
    }

    @Override
    public List<Customer> findAll() {
        System.out.println("Customers count: " + customers.size());
        return new ArrayList<>(customers);
    }

    @Override
    public Customer save(Customer customer) {
        if (customer.getId() == null) {
            customer.setId(idCounter.getAndIncrement());
            customers.add(customer);
        } else {
            Customer existing = findById(customer.getId());
            if (existing != null) {
                existing.setName(customer.getName());
                existing.setEmail(customer.getEmail());
            }
        }
        return customer;
    }

    @Override
    public Customer findById(Long id) {
        return customers.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void remove(Long id) {
        customers.removeIf(c -> c.getId().equals(id));
    }
}