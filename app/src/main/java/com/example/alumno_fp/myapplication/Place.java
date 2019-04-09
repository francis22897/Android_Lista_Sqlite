package com.example.alumno_fp.myapplication;

public class Place {

    private int id;
    private String place;
    private String comments;

    public Place(int id, String place, String comments) {
        this.id = id;
        this.place = place;
        this.comments = comments;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
