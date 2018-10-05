package com.example.huuduc.intership_project.data.model;

import java.io.Serializable;

public class Search implements Serializable {

    private District district;
    private Ward ward;
    private int priceStart;
    private int priceEnd;

    public Search(District district, Ward ward, int priceStart, int priceEnd) {
        this.district = district;
        this.ward = ward;
        this.priceStart = priceStart;
        this.priceEnd = priceEnd;
    }

    public Search() {
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Ward getWard() {
        return ward;
    }

    public void setWard(Ward ward) {
        this.ward = ward;
    }

    public int getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(int priceStart) {
        this.priceStart = priceStart;
    }

    public int getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(int priceEnd) {
        this.priceEnd = priceEnd;
    }
}
