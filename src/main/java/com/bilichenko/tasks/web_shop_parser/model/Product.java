package com.bilichenko.tasks.web_shop_parser.model;

import java.util.List;

public class Product {

    private String articelId;
    private String name;
    private String brand;
    private List<String> colors;
    private Double price;

    public void setArticelId(String articelId) {
        this.articelId = articelId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}