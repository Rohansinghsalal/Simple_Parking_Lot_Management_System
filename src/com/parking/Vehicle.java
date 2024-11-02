package com.parking;

public class Vehicle {
    private String type;
    private String registration;
    private String color;
    private String name;

    public Vehicle(String type, String registration, String color, String name) {
        this.type = type;
        this.registration = registration;
        this.color = color;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getRegistration() {
        return registration;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }
}
