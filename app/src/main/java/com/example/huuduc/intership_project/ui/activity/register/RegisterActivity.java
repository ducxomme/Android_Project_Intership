package com.example.huuduc.intership_project.ui.activity.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.widget.Button;
import android.widget.TextView;

import com.example.huuduc.intership_project.R;
import com.example.huuduc.intership_project.ui.activity.login.LoginActivity;
import com.example.huuduc.intership_project.ui.base.BaseActivity;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements IRegisterView {

    private RegisterPresenter mRegisterPresenter;

    @BindView(R.id.edUsername)
    TextInputEditText edUsername;
    @BindView(R.id.tvUsername)
    TextView tvUsername;

    @BindView(R.id.edEmail)
    TextInputEditText edEmail;
    @BindView(R.id.tvEmail)
    TextView tvEmail;
    @BindView(R.id.edPassword)
    TextInputEditText edPassword;
    @BindView(R.id.tvPassword)
    TextView tvPassword;
    @BindView(R.id.edConfirmPass)
    TextInputEditText edConfirmPass;
    @BindView(R.id.tvConfirmPass)
    TextView tvConfirmPass;
    @BindView(R.id.btnRegister)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        addControls();

        textChangeListener();
    }

    private void addControls() {
        edUsername = findViewById(R.id.edUsername);
        tvUsername = findViewById(R.id.tvUsername);
        edEmail = findViewById(R.id.edEmail);
        tvEmail = findViewById(R.id.tvEmail);
        edPassword = findViewById(R.id.edPassword);
        tvPassword = findViewById(R.id.tvPassword);
        edConfirmPass = findViewById(R.id.edConfirmPass);
        tvConfirmPass = findViewById(R.id.tvConfirmPass);

        mRegisterPresenter = new RegisterPresenter(this);
    }

    @OnClick(R.id.btnRegister)
    void onClick() {
        String username = edUsername.getText().toString().trim(),
                email = edEmail.getText().toString().trim(),
                password = edPassword.getText().toString().trim(),
                confirmPass = edConfirmPass.getText().toString().trim();
        if (isValidUsername(username) && isValidEmail(email)
                && isValidPass(password)
                && isPassMatching(password, confirmPass)) {
            hideKeyboard();
            btnRegister.setEnabled(false);
            btnRegister.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.colorDisable));
        }
        if (isConnected()){
            mRegisterPresenter.handleRegister(email, username, password);
        }else{
            showMessage("Sign Up Failed");
        }
    }


    private void textChangeListener() {
        edUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isValidUsername(editable.toString())) {
                    tvUsername.setText(R.string.invalid_username);
                } else
                    tvUsername.setText("");
            }
        });
        edEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isValidName(editable.toString())) {
                    tvEmail.setText(R.string.invalid_email);
                } else {
                    tvEmail.setText("");
                }
            }
        });
        edPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isValidPass(editable.toString())) {
                    tvPassword.setText(R.string.invalid_password);
                } else {
                    tvPassword.setText("");
                }
            }
        });
        edConfirmPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!isPassMatching(edPassword.getEditableText().toString(), editable.toString())) {
                    tvConfirmPass.setText(R.string.pass_not_matching);
                } else {
                    tvConfirmPass.setText("");
                }
            }
        });
    }

    private boolean isPassMatching(String pass, String confirm) {
        if (pass.equals(confirm))
            return true;
        return false;
    }

    private boolean isValidPass(String pass) {
        if (pass.length() < 6) {
            return false;
        }
        return true;
    }

    private boolean isValidUsername(String username) {
        if (username.length() >= 6 && username.length() <= 20) {
            return true;
        } else {
            return false;
        }
    }

    private boolean isValidName(String name){
        if (name.length() > 0 && name.length() < 50)
            return true;
        return false;
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void registerSuccess() {
        showMessage("Sign Up Successful");
        startActivity(new Intent(this, LoginActivity.class));
    }
}
