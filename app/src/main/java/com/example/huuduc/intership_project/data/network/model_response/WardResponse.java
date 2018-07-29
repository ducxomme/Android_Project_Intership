package com.example.huuduc.intership_project.data.network.model_response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class WardResponse implements Parcelable{

    @SerializedName("name")
    private String name;
    @SerializedName("type")
    private String type;

    public WardResponse() {
    }

    public static final Parcelable.Creator<WardResponse> CREATOR = new Creator<WardResponse>() {
        @Override
        public WardResponse createFromParcel(Parcel parcel) {
            return new WardResponse(parcel);
        }

        @Override
        public WardResponse[] newArray(int i) {
            return new WardResponse[i];
        }
    };
    public WardResponse(Parcel in) {
        super();
        name = in.readString();
        type = in.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
