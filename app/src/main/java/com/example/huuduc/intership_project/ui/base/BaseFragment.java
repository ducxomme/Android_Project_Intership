package com.example.huuduc.intership_project.ui.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.ButterKnife;
import butterknife.internal.Utils;
import cn.pedant.SweetAlert.SweetAlertDialog;

public abstract class BaseFragment<T extends BaseActivity> extends Fragment implements BaseView {

    protected T mActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (T) context;
    }

    @LayoutRes
    public abstract int contentViewLayout();

    public abstract void initializeLayout(View view);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(contentViewLayout(), container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initializeLayout(view);
    }

    protected void hideKeyboardOutside(View view) {
        //Set up touch listener for non-text box views to hide keyboard.
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(
                    (v, event) -> {
                        mActivity.hideSoftKeyboard(view);
                        v.clearFocus();
                        return false;
                    });
        }
        //If a layout container, iterate over children
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                hideKeyboardOutside(innerView);
            }
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, Bundle bun, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        intent.putExtras(bun);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, int requestCode) {
        Intent intent = new Intent(getContext(), clazz);
        startActivityForResult(intent, requestCode);
        mActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void backToPrevious(Bundle bundle) {
        mActivity.backToPrevious(bundle);
    }

    public void showLoading(String message) {
        mActivity.showLoading(message);
    }

    public void finishLoading(String message, boolean isSuccess) {
        mActivity.hideLoading(message,isSuccess);
    }

    public void hideLoading() {
        mActivity.hideLoading();
    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    @Override
    public void hideLoading(String message, boolean isSuccess) {
        mActivity.hideLoading(message, isSuccess);
    }

    @Override
    public void showMessage(String title, int message, int messageType) {
        mActivity.showMessage(title, message, messageType);
    }

    @Override
    public void showMessage(String title, String message, int messageType) {
        mActivity.showMessage(title,message, messageType);
    }

    @Override
    public void hideKeyboard() {
        mActivity.hideKeyboard();
    }
}
