package com.example.md4b10th3.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product, Integer> products = new HashMap<>();

    public Cart() {}

    public Map<Product, Integer> getProducts() {
        return products;
    }

    // Thêm sản phẩm
    public void addProduct(Product product) {
        products.merge(product, 1, Integer::sum);
    }

    // Cập nhật số lượng sản phẩm
    public void updateProductQuantity(Product product, int quantity) {
        Product keyToUpdate = null;
        for (Product key : products.keySet()) {
            if (key.getId().equals(product.getId())) {
                keyToUpdate = key;
                break;
            }
        }
        if (keyToUpdate != null) {
            if (quantity <= 0) {
                products.remove(keyToUpdate);
            } else {
                products.put(keyToUpdate, quantity);
            }
        }
    }


    // Xóa sản phẩm
    public void removeProduct(Product product) {
        Product keyToRemove = null;
        for (Product key : products.keySet()) {
            if (key.getId().equals(product.getId())) {
                keyToRemove = key;
                break;
            }
        }
        if (keyToRemove != null) {
            products.remove(keyToRemove);
        }
    }


    // Tổng số lượng sản phẩm (tổng số món, ví dụ: 3 sản phẩm A + 2 sản phẩm B = 5)
    public Integer countProductQuantity() {
        return products.values().stream().mapToInt(Integer::intValue).sum();
    }

    // Số loại sản phẩm
    public Integer countItemQuantity() {
        return products.size();
    }

    // Tổng tiền thanh toán
    public Float countTotalPayment() {
        return (float) products.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().getPrice() * entry.getValue())
                .sum();
    }
}
