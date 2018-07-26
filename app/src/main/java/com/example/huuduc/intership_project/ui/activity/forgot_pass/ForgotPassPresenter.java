package com.example.huuduc.intership_project.ui.activity.forgot_pass;

import android.support.annotation.NonNull;

import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassPresenter extends BasePresenter implements IForgotPassPresenter{
    private IForgotPassView mView;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public ForgotPassPresenter(IForgotPassView mView) {
        this.mView = mView;
    }

    public void handleResetPass(String emailInput) {
        mView.showLoading("Đang gửi");
        mAuth.sendPasswordResetEmail(emailInput)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            mView.success();
                        } else {
                            mView.failed();
                        }
                        mView.hideLoading();
                    }
                });
    }
}
