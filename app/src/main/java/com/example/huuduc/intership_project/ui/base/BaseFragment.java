package com.example.huuduc.intership_project.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Unbinder;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {

    protected Unbinder mUnBinder;
    private SweetAlertDialog mSweetAlertDialog;
    protected P mPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public Context getBaseContext() {
        return getContext();
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void dismissDialog() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void hideKeyboard() {

    }
}
