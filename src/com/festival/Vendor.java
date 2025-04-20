package com.festival;

public class Vendor implements Displayable {
    final String name;
    final VendorType type;

    public Vendor(String name, VendorType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public void displayDetails() {
        System.out.println("Vendor: " + name + ", Type: " + type);
    }

    public enum VendorType {
        FOOD, MERCHANDISE, DRINKS
    }
}