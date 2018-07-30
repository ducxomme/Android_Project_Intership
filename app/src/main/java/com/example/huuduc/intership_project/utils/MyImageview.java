package com.example.huuduc.intership_project.utils;

import android.content.Context;
import android.util.AttributeSet;


public class MyImageview extends android.support.v7.widget.AppCompatImageView {
    public MyImageview (Context context) {
        super(context);
    }

    public MyImageview (Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyImageview (Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
     }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getMeasuredWidth(), getMeasuredWidth()); // Snap to
        // width
    }
}