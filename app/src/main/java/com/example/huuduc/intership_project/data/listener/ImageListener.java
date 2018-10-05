package com.example.huuduc.intership_project.data.listener;

import java.util.List;

public interface ImageListener {

    void success(List<String> listImage);

    void failed(String error);
}
