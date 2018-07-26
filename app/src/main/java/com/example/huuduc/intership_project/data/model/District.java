package com.example.huuduc.intership_project.data.model;

import java.util.Map;

public class District {
    private String district;
    private String name;
    private Map<String, Ward> ward;

    public District() {
    }

    public District(String district, String name, Map<String, Ward> ward) {
        this.district = district;
        this.name = name;
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Ward> getWard() {
        return ward;
    }

    public void setWard(Map<String, Ward> ward) {
        this.ward = ward;
    }

    @Override
    public String toString() {
        return name;
    }
}
