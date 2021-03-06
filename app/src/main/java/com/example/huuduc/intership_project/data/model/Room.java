package com.example.huuduc.intership_project.data.model;

import java.io.Serializable;

public class Room implements Serializable{
    public String id;
    public String user_id;
    public String ward;
    public String wardId;
    public String district;
    public String districtId;
    public int area;
    public int price;
    public boolean isPublic;
    public String date_public;
    public String image;
    public String address;
    public int seen;
    public int room_empty;
    public String rating;
    public String description;
    public String phone;

    public Room() {
    }

    public Room(String id, String user_id, String ward, String wardId, String district, String districtId, int area, int price, Boolean isPublic, String date_public, String image, String address, int seen, int room_empty, String rating, String description, String phone) {
        this.id = id;
        this.user_id = user_id;
        this.ward = ward;
        this.wardId = wardId;
        this.district = district;
        this.districtId = districtId;
        this.area = area;
        this.price = price;
        this.isPublic = isPublic;
        this.date_public = date_public;
        this.image = image;
        this.address = address;
        this.seen = seen;
        this.room_empty = room_empty;
        this.rating = rating;
        this.description = description;
        this.phone = phone;
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

    public String getWardId() {
        return wardId;
    }

    public void setWardId(String wardId) {
        this.wardId = wardId;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
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

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
