package com.example.iss_management_vanzari.models;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private Double price;

    public Product() {}

    public Product(String name, Integer quantity, String origin, Double price) {
        this.name = name;
        this.quantity = quantity;
        this.origin = origin;
        this.price = price;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void subtractQuantity(int amount) {
        int newQuantity = this.quantity - amount;
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Cannot subtract more than current quantity.");
        }
        this.quantity = newQuantity;
    }
}
