package com.example.alumno_fp.myapplication;

import com.google.gson.Gson;

import java.util.ArrayList;

public class Places {

    private ArrayList<Place> placesList;

    public Places() {
        this.placesList = new ArrayList<>();
    }

    public Places(ArrayList<Place> placesList) {
        this.placesList = placesList;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }

    public Places fromJSON(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, Places.class);
    }

    public ArrayList getPlacesList() {
        return placesList;
    }

    public void addPlace(Place place){
        placesList.add(place);
    }

    public void removePlace(Place place){
        placesList.remove(place);
    }

    @Override
    public String toString() {
        return "Places{" +
                "placesList=" + placesList +
                '}';
    }
}
