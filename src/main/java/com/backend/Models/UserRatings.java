package com.backend.Models;

import java.util.List;
import java.util.Map;

public class UserRatings {
    private String userName;
    private List<String> bookMarkedStores;
    private Map<String, Integer> ratings;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getBookMarkedStores() {
        return bookMarkedStores;
    }

    public void setBookMarkedStores(List<String> bookMarkedStores) {
        this.bookMarkedStores = bookMarkedStores;
    }

    public Map<String, Integer> getRatings() {
        return ratings;
    }

    public void setRatings(Map<String, Integer> ratings) {
        this.ratings = ratings;
    }
}
