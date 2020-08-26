package com.company.model.room;

import java.util.ArrayList;
import java.util.List;

public class Room {
    private long id;
    private int number;
    private double price;
    private String comfort;
    private int sits;
    private List<String> descriptions = new ArrayList<>();

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public double getPrice() {
        return price;
    }

    public int getSits() {
        return sits;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSits(int sits) {
        this.sits = sits;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addDescription(String description){
        descriptions.add(description);
    }
}
