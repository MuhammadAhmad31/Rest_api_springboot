package com.api.demo_rest.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //    contoh untuk buat column
    @NotEmpty(message = "Name is required")
    @Column(name = "name", length = 100, unique = false)
    private String name;

    @NotEmpty(message = "Description is required")
    @Column(length = 500)
    private String description;

    private Double price;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price) {
        Id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
