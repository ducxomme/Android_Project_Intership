package com.example.huuduc.intership_project.data.model;

import java.util.HashMap;

public class User {
    private String username;
    private String email;
    private String id;
    private Boolean gender;
    private HashMap<String, String> like;
    private HashMap<String, String> rooms;
    private String phone;

    public User() {
    }

    public User(String username, String email, String id, Boolean gender, HashMap<String, String> like, HashMap<String, String> rooms, String phone) {
        this.username = username;
        this.email = email;
        this.id = id;
        this.gender = gender;
        this.like = like;
        this.rooms = rooms;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public HashMap<String, String> getLike() {
        return like;
    }

    public void setLike(HashMap<String, String> like) {
        this.like = like;
    }

    public HashMap<String, String> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, String> rooms) {
        this.rooms = rooms;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
