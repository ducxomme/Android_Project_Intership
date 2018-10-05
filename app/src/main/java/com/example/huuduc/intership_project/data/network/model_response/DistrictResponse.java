package com.example.huuduc.intership_project.data.network.model_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class DistrictResponse implements Parcelable{

    @SerializedName("districtid")
    private int districtid;
    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

    public static final Parcelable.Creator<DistrictResponse> CREATOR = new Creator<DistrictResponse>() {
        @Override
        public DistrictResponse createFromParcel(Parcel parcel) {
            return new DistrictResponse(parcel);
        }

        @Override
        public DistrictResponse[] newArray(int i) {
            return new DistrictResponse[i];
        }
    };
    public DistrictResponse(Parcel in) {
        super();
        districtid = in.readInt();
        name = in.readString();
        type = in.readString();
    }

    public DistrictResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDistrictid() {
        return districtid;
    }

    public void setDistrictid(int districtid) {
        this.districtid = districtid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(districtid);
        parcel.writeString(name);
        parcel.writeString(type);
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
