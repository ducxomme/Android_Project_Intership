package com.example.huuduc.intership_project.data.model;

import java.util.List;

public class RoomCategory {
    private String categoryName;
    private List<Room> listRoom;

    public RoomCategory(String categoryName, List<Room> listRoom) {
        this.categoryName = categoryName;
        this.listRoom = listRoom;
    }

    public RoomCategory() {
    }

    public List<Room> getListRoom() {
        return listRoom;
    }

    public void setListRoom(List<Room> listRoom) {
        this.listRoom = listRoom;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
