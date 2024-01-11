package com.example.tourguide;



public class Places
{

    String name, description;
    double latitude, longitude;

    public Places() {
    }

    public Places(String name) {
        this.name = name;
    }

    public Places(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Places(String name, String description, double latitude, double longitude) {
        this.name = name;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }

  
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {

        this.name = name;
    }

    public String getDescription()
    {

        return description;
    }

    public void setDescription(String description)
    {

        this.description = description;
    }

    public double getLatitude()
    {

        return latitude;
    }

    public void setLatitude(double latitude)
    {

        this.latitude = latitude;
    }

    public double getLongitude()
    {

        return longitude;
    }

    public void setLongitude(double longtitude) {


        this.longitude = longtitude;
    }
}
