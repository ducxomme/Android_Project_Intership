package com.example.huuduc.intership_project.data.network.model_request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("username")
    public String username;
    @SerializedName("role_id")
    public String roleId;
    @SerializedName("password")
    public String password;
    @SerializedName("name")
    public String name;

    public RegisterRequest(String username, String roleId, String password, String name) {
        this.username = username;
        this.roleId = roleId;
        this.password = password;
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
