package com.example.huuduc.intership_project.data.model;

import java.io.Serializable;
import java.util.Map;

public class Ward implements Serializable{
    private String id;
    private String name;
    private Map<String, String> room;

    public Ward() {
    }

    public Ward(String id, String name, Map<String, String> room) {
        this.id = id;
        this.name = name;
        this.room = room;
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

    public Map<String, String> getRoom() {
        return room;
    }

    public void setRoom(Map<String, String> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return name;
    }
}


