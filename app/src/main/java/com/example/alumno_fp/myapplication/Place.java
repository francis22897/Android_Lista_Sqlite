package com.example.alumno_fp.myapplication;

public class Place {

    private String place;

    public Place(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Place{" +
                "place='" + place + '\'' +
                '}';
    }
}
