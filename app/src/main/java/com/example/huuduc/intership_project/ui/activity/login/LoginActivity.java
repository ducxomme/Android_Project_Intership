package com.example.huuduc.intership_project.ui.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.ui.activity.forgot_pass.ForgotPassActivity;
import com.example.huuduc.intership_project.ui.activity.main.MainActivity;
import com.example.huuduc.intership_project.ui.activity.register.RegisterActivity;
import com.example.huuduc.intership_project.ui.base.BaseActivity;
import com.example.huuduc.intership_project.utils.DatabaseService;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView{

    private LoginPresenter mLoginPresenter;

    @BindView(R.id.edEmail)
    EditText edEmail;
    @BindView(R.id.edPassword)
    EditText edPassword;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.tvForgotPass)
    TextView tvForgotPass;
    @BindView(R.id.btnSignup)
    TextView btnSignup;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        String userId = DatabaseService.getUserID();
        if (!TextUtils.isEmpty(userId)){
            goToMainActivity();
        }
    }

    private void goToMainActivity() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @OnClick({R.id.btnLogin, R.id.btnSignup, R.id.tvForgotPass})
    void onClick(View view){
        switch (view.getId()){
            case R.id.btnLogin:
                String email = edEmail.getText().toString().trim();
                String password = edPassword.getText().toString().trim();

                if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    showLoading("Đăng nhập");
                    mLoginPresenter.onClickLogin(email, password);
                }
                break;
            case R.id.btnSignup:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.tvForgotPass:
                startActivity(new Intent(LoginActivity.this, ForgotPassActivity.class));
                break;
        }

    }

    @Override
    public void loginSuccess() {
        hideLoading();
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void loginFailed(String error) {
        hideLoading(error, false);
    }
}
