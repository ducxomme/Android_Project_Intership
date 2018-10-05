package com.example.huuduc.intership_project.data.listener;

import com.example.huuduc.intership_project.data.model.Rating;

public interface RatingListener {
    void OnSuccess(double rating);
    void CallBackRatingGet(Rating rating);
}
