package com.example.tourguide;

public class ExampleModel {
     String examplename;
     String exampleAbbreviation;
     String exampleAbbreviationSmall;
     int image;

    public ExampleModel(String examplename, String exampleAbbreviation, String exampleAbbreviationSmall, int image) {
        this.examplename = examplename;
        this.exampleAbbreviation = exampleAbbreviation;
        this.exampleAbbreviationSmall = exampleAbbreviationSmall;
        this.image = image;
    }

    public static void add(ExampleModel exampleModel) {
    }

    public String getExamplename() {

        return examplename;
    }

    public String getExampleAbbreviation() {

        return exampleAbbreviation;
    }

    public String getExampleAbbreviationSmall() {

        return exampleAbbreviationSmall;
    }

    public int getImage() {

        return image;
    }
}
