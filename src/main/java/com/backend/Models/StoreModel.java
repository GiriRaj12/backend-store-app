package com.backend.Models;

import java.util.List;

public class StoreModel {
    private String id;

    private String name;

    private String categories;

    private String address;

    private List<Double> geoLocations;

    private String ipAddress;

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

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
