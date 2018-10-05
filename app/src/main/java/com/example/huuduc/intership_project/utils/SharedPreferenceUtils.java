package com.example.huuduc.intership_project.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.huuduc.intership_project.data.model.User;
import com.google.gson.Gson;

public class SharedPreferenceUtils {
    private static final String USER = "USER";

    public static void saveUser(Context context, User user){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(user);

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(Constant.USER, json);
        editor.apply();

    }

    public static User getUser(Context context){
        SharedPreferences preferences = context.getSharedPreferences(Constant.KEY_PREFERENCES, Context.MODE_PRIVATE);
        String userString = preferences.getString(Constant.USER, "");
        Gson gson = new Gson();
        User user = gson.fromJson(userString, User.class);
        return user;
    }
}
