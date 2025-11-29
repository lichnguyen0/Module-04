package com.example.md4b10th3.service;

import com.example.md4b10th3.model.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAll();

    Optional<Product> findById(Long id);
}
