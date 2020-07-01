package com.backend.Models;

import java.util.List;
import java.util.Map;

public class StoreModel {
    private String id;

    private String name;

    private String categories;

    private String address;

    private List<Double> geoLocations;

    private Map<String, Integer> ratingsMap;

    public Map<String, Integer> getRatingsMap() {
        return ratingsMap;
    }

    public void setRatingsMap(Map<String, Integer> ratingsMap) {
        this.ratingsMap = ratingsMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Double> getGeoLocations() {
        return geoLocations;
    }

    public void setGeoLocations(List<Double> geoLocations) {
        this.geoLocations = geoLocations;
    }
}
