package com.festival;

public interface Displayable {
    default void displayDetails() {
        System.out.println(getDescription());
    }

    private String getDescription() {
        return "No details available.";
    }
}