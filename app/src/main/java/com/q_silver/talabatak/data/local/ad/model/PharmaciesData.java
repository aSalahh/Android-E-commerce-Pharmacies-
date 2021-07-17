package com.q_silver.talabatak.data.local.ad.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "pharmacies")
public class PharmaciesData {
 @PrimaryKey(autoGenerate = true)
    private  int id;
    private String name;
    Integer  imageUrl;
    String desc;
    String location;

    public PharmaciesData(int id, String name, Integer imageUrl, String desc, String location) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.location = location;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
