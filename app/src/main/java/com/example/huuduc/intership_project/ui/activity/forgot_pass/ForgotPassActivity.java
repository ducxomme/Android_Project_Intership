package com.example.huuduc.intership_project.ui.activity.forgot_pass;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class ForgotPassActivity extends BaseActivity implements IForgotPassView{

    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.btn_reset_password)
    Button btnReset;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ForgotPassPresenter mPresenter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mPresenter = new ForgotPassPresenter(this);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick({R.id.btn_reset_password})
    void onClick(View view){
        String emailInput = email.getText().toString().trim();
        if (!TextUtils.isEmpty(emailInput)) {
            mPresenter.handleResetPass(emailInput);
        }
    }

    @Override
    public void success() {
        showMessage("Thông báo","Chúng tôi đã gửi Email reset mật khẩu cho bạn", SweetAlertDialog.SUCCESS_TYPE);
        finish();
    }

    @Override
    public void failed() {
        showMessage("Lỗi","Đã có lỗi xảy ra! Vui lòng thử lại sau", SweetAlertDialog.ERROR_TYPE);
        finish();
    }
}
