package com.example.huuduc.intership_project.ui.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BaseActivity extends AppCompatActivity implements BaseView {

    private ProgressDialog mProgressDialog;
    private SweetAlertDialog mSweetAlertDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void showLoading(String message) {
        if (!isFinishing()) {
            if (mSweetAlertDialog == null || !mSweetAlertDialog.isShowing()) {
                mSweetAlertDialog = new SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE);
                mSweetAlertDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                mSweetAlertDialog.setTitleText(message);
                mSweetAlertDialog.setCancelable(false);
                mSweetAlertDialog.show();
            } else {
                mSweetAlertDialog.setTitleText(message);
            }
        }
    }
    @Override
    public void showMessage(String title, int message, int messageType) {
        if (!isFinishing()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, messageType);
            sweetAlertDialog.setTitleText(title);
            sweetAlertDialog.setContentText(getString(message));
            sweetAlertDialog.setConfirmText("OK");
            sweetAlertDialog.setCanceledOnTouchOutside(true);
            sweetAlertDialog.show();
        }
    }

    @Override
    public void showMessage(String title, String message, int messageType) {
        if (!isFinishing()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(this, messageType);
            sweetAlertDialog.setTitleText(title);
            sweetAlertDialog.setContentText(message);
            sweetAlertDialog.setConfirmText("OK");
            sweetAlertDialog.setCanceledOnTouchOutside(true);
            sweetAlertDialog.show();
        }
    }
    @Override
    public void hideLoading(String message, boolean isSuccess) {
        if (!isFinishing()) {
            if (mSweetAlertDialog.isShowing()) {
                mSweetAlertDialog.setCanceledOnTouchOutside(true);
                mSweetAlertDialog.setTitleText(message);
                mSweetAlertDialog.setConfirmText("OK");
                if (isSuccess) {
                    mSweetAlertDialog.changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                } else {
                    mSweetAlertDialog.changeAlertType(SweetAlertDialog.ERROR_TYPE);
                }
            }
        }
    }

    @Override
    public void hideLoading() {
        if (!isFinishing()) {
            if (mSweetAlertDialog.isShowing()) {
                mSweetAlertDialog.dismissWithAnimation();
            }
        }
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public Context getBaseContext() {
        return super.getBaseContext();
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz) {
        goNextScreen(clazz, null, false);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, Bundle bun) {
        goNextScreen(clazz, bun, false);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, boolean isFinishAll) {
        goNextScreen(clazz, null, isFinishAll);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, Bundle bun, boolean isFinishAll) {
        Intent intent = new Intent(this, clazz);
        if (bun != null) {
            intent.putExtras(bun);
        }
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        if (isFinishAll) {
            finishAffinity();
        }
    }

    public void restartActivity(Bundle bun) {
        Intent intent = getIntent();
        intent.putExtras(bun);
        finish();
        startActivity(intent);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public <T extends Activity> void goNextScreen(Class<T> clazz, Bundle bun, int requestCode) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(bun);
        startActivityForResult(intent, requestCode);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @Override
    public void backToPrevious(Bundle bundle) {
        Intent intent = new Intent();
        intent.putExtras(bundle);
        setResult(RESULT_OK, intent);
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public  void hideSoftKeyboard(View view) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) view.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }


}
