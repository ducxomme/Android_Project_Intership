package com.example.huuduc.intership_project.ui.base;

import android.content.Context;

public interface BaseView {
    Context getBaseContext();
    void showDialog();
    void dismissDialog();
    void showMessage(String message);
    void hideKeyboard();
}
