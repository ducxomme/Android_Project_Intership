package com.example.huuduc.intership_project.ui.activity.login;

import android.support.annotation.NonNull;

import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;

import static com.example.huuduc.intership_project.utils.Constant.USER_REFERENCE;

public class LoginPresenter extends BasePresenter implements ILoginPresenter{

    private ILoginView iLoginView;
    DatabaseService mDatabase = DatabaseService.getInstance();
    DatabaseReference mUserRef = mDatabase.createDatabase(USER_REFERENCE);

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
    }

    @Override
    public void onClickLogin(String email, String password) {
        mDatabase.getFirebaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                iLoginView.loginSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                iLoginView.loginFailed(e.getMessage());
            }
        });
    }
}
