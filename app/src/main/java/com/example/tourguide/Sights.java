package com.example.tourguide;

import android.media.Image;

public class Sights {
    private final String name;
    private final String vicinity;
    private final String rating;
    private final String type;

    public Image getImage() {
        return image;
    }

    private final Image image;

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
    public Sights(String name, String vicinity, String rating, String type, Image image) {
        this.name = name;
        this.vicinity = vicinity;
        this.rating = rating;
        this.type = type;
        this.image = image;
    }


}
