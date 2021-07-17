package com.q_silver.talabatak.data.local.ad.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "orders")
public class Order {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    Integer  imageUrl;
    double price;
    String type;
    String description;
    int count;

    public Order(int id, String name, Integer imageUrl, double price, String type, String description, int count) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.type = type;
        this.description = description;
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
