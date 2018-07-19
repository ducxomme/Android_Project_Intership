package com.example.huuduc.intership_project.data.model;

public class Room {
    public String id;
    public String user_id;
    public String ward;
    public String district;
    public int area;
    public int price;
    public String date_public;
    public String image;
    public String address;
    public int seen;
    public int room_empty;

    public Room() {
    }

    public Room(String id, String user_id, String ward, String district, int area, int price, String date_public, String image, String address, int seen, int room_empty) {
        this.id = id;
        this.user_id = user_id;
        this.ward = ward;
        this.district = district;
        this.area = area;
        this.price = price;
        this.date_public = date_public;
        this.image = image;
        this.address = address;
        this.seen = seen;
        this.room_empty = room_empty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate_public() {
        return date_public;
    }

    public void setDate_public(String date_public) {
        this.date_public = date_public;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getSeen() {
        return seen;
    }

    public void setSeen(int seen) {
        this.seen = seen;
    }

    public int getRoom_empty() {
        return room_empty;
    }

    public void setRoom_empty(int room_empty) {
        this.room_empty = room_empty;
    }
}
