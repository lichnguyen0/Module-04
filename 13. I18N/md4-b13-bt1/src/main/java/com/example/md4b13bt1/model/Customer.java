package com.example.md4b13bt1.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Customer {
    private Long id;

    @NotBlank(message = "{customer.name.notblank}")
    @Size(min = 2, max = 50, message = "{customer.name.size}")
    private String name;

    @NotBlank(message = "{customer.email.notblank}")
    @Email(message = "{customer.email.invalid}")
    private String email;

    public Customer() {}

    public Customer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
