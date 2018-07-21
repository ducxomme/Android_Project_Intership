package com.example.huuduc.intership_project.ui.activity.register;

import android.support.annotation.NonNull;
import com.example.huuduc.intership_project.data.model.User;
import com.example.huuduc.intership_project.ui.base.BasePresenter;
import com.example.huuduc.intership_project.utils.DatabaseService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import static com.example.huuduc.intership_project.utils.Constant.USER_REFERENCE;

public class RegisterPresenter extends BasePresenter implements IRegisterPresenter{

    private IRegisterView iRegisterView;

    DatabaseService mDatabase = DatabaseService.getInstance();
    DatabaseReference mUserRef = mDatabase.createDatabase(USER_REFERENCE);

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }

    public void handleRegister(final String email, final String username, final String password) {

        iRegisterView.showLoading("Loading...");
        mDatabase.getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User(username, email, task.getResult().getUser().getUid());
                    addNewUserFirebase(user);
                }
            }
        });
    }

    private void addNewUserFirebase(final User user) {
        final DatabaseReference mRef = mUserRef.child(user.getId());
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mRef.setValue(user);
                iRegisterView.registerSuccess();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
