package com.example.tourguide;

public class Places {

    String name, description;
    double latitude, longtitude;

    public Places(){}

    public Places(String name, double latitude, double longtitude) {
        this.name = name;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public Places(String name, String description, double latitude, double longtitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longtitude = longtitude;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }
}
