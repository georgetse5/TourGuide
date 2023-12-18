package com.example.tourguide;

public class Sights {
    private final String name;
    private final String vicinity;
    private final String rating;
    private final String type;
    public String getName() {
        return name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public String getRating() {
        return rating;
    }

    public String getType() {
        return type;
    }
    public Sights(String name, String vicinity, String rating, String type) {
        this.name = name;
        this.vicinity = vicinity;
        this.rating = rating;
        this.type = type;

    }


}
