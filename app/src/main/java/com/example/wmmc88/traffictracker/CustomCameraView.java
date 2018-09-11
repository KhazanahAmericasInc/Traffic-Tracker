package com.example.wmmc88.traffictracker;

import android.content.Context;
import android.util.AttributeSet;

import org.opencv.android.JavaCameraView;

//Todo switch to java camera view 2
public class CustomCameraView extends JavaCameraView {
    private static final String TAG = CustomCameraView.class.getSimpleName();

    public CustomCameraView(Context context, int cameraId) {
        super(context, cameraId);
    }

    public CustomCameraView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
