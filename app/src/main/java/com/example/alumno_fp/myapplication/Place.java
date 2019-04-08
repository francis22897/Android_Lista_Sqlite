package com.example.alumno_fp.myapplication;

import java.util.UUID;

public class Place {

    private String id;
    private String place;
    private String comments;

    public Place(String place, String comments) {
        this.id = UUID.randomUUID().toString();
        this.place = place;
        this.comments = comments;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", comments='" + comments + '\'' +
                '}';
    }
}
