package com.example.md4b3btsudungthymeleafchoungdung.service;

import com.example.md4b3btsudungthymeleafchoungdung.model.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService implements IProductService {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "iPhone 15", 23000000, "Flagship Apple", "Apple"));
        products.add(new Product(2, "Samsung S24", 21000000, "Flagship Samsung", "Samsung"));
    }

    @Override
    public List<Product> findAll() { return products; }

    @Override
    public Product findById(int id) {
        return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    @Override
    public void save(Product product) { products.add(product); }

    @Override
    public void update(int id, Product product) {
        Product p = findById(id);
        if (p != null) {
            p.setName(product.getName());
            p.setPrice(product.getPrice());
            p.setDescription(product.getDescription());
            p.setManufacturer(product.getManufacturer());
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(p -> p.getId() == id);
    }

    @Override
    public List<Product> searchByName(String name) {
        if (name == null || name.isEmpty()) return new ArrayList<>();
        return products.stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
