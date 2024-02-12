package com.cloudbees.ecommerce.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;

    private String name;

    private String description;

    private double price;

    private int quantity;

    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Product id:").append(this.getId()).append(", Name:").append(this.getName()).append(", Description:").append(this.getDescription()).append(", Price:").append(this.getPrice()).append(", Quantity:").append(this.getQuantity());
        return sb.toString();
    }

}
