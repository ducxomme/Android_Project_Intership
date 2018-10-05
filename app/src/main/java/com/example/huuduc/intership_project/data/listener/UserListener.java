package com.example.huuduc.intership_project.data.listener;

import com.example.huuduc.intership_project.data.model.User;

public interface UserListener {
    void success(User user);
    void failed(String error);
}
