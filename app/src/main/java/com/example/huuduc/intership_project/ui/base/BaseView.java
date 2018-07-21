package com.example.huuduc.intership_project.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;

public interface BaseView {
    Context getBaseContext();
    void hideLoading();
    void hideLoading(String message, boolean isSuccess);
    void showLoading(String message);
    void showMessage(String title, @StringRes int message, int messageType);
    void showMessage(String title, String message, int messageType);
    void backToPrevious(Bundle bundle);
    void hideKeyboard();
}
