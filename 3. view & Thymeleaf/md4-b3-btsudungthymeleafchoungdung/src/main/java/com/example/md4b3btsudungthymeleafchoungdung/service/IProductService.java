package com.example.md4b3btsudungthymeleafchoungdung.service;

import com.example.md4b3btsudungthymeleafchoungdung.model.Product;
import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(int id, Product product);
    void delete(int id);
    List<Product> searchByName(String name);
}
