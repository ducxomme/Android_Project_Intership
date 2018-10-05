package com.example.huuduc.intership_project.data.listener;

import java.util.List;

public interface CallBackListener<T> {
    void onSucess(List<T> objects);
    void onFailed(String message);
}
