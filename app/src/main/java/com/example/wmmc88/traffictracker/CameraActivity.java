package com.example.wmmc88.traffictracker;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import org.opencv.core.Size;

public class CameraActivity extends AppCompatActivity implements CustomCameraView.CvCameraViewListener2 {
    public static final int CAMERA_PERMISSION_REQUEST = 1;
    private static final String TAG = CameraActivity.class.getSimpleName();

    BaseLoaderCallback mLoaderCallBack = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status){
                case BaseLoaderCallback.SUCCESS:{
                    javaCameraView.enableView();
                    break;
                }
                default:{
                    super.onManagerConnected(status);
                    break;
                }
            }
        }
    };

    /*
    static {
        if (!OpenCVLoader.initDebug()) {
            Log.e(TAG, "ERROR. COULD NOT LOAD STATIC OPENCV LIBRARIES!!!");
            //TODO Handler to finish activty
        } else {
            Log.i(TAG, "Opencv loaded successfully.");
//            Other OpenCV JNI libs should be here:
//            System.loadLibrary("my_jni_lib1");
//            System.loadLibrary("my_jni_lib2");
        }
    }*/

    private CameraBridgeViewBase mOpenCvCameraView;
    private Mat mRgb;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mPreviewFrameWidth;
    private int mPreviewFrameHeight;
    private String currLoc;

    private KCFTrackerCountingSolution mKCFTrackerCountingSolution;

    JavaCameraView javaCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "ONCREATE*******************************");
        setContentView(R.layout.cam_activity);
        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    private boolean permissionsGranted() {
        Log.d(TAG, "checkPermissionsGranted");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Camera Permissions Granted!");
                    loadOpenCVView();
                } else {
                    Log.w(TAG, "Permissions Denied!");
                    this.finish();
                }
                return;
            }

            //other permission request cases
        }
    }

    private void loadOpenCVView() {
        Log.d(TAG, "loadOpenCVView");

        mOpenCvCameraView = findViewById(R.id.cbvb_camera);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);
        mOpenCvCameraView.setMaxFrameSize(640, 480);
        mOpenCvCameraView.enableView();
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if(OpenCVLoader.initDebug()) {
            Log.i(TAG, "SUCCESS: Opencv load successful.");
            mLoaderCallBack.onManagerConnected((LoaderCallbackInterface.SUCCESS));
        }else{
            Log.i(TAG, "WARNING: Opencv load unsuccessful.");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, this, mLoaderCallBack);
        }
    }


    @Override
    public void onPause() {
        Log.d(TAG, "onPause");

        super.onPause();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        super.onDestroy();
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
        Log.d(TAG, "onCameraViewStarted");

        Toast.makeText(this, width + "x" + height, Toast.LENGTH_LONG).show();

        android.graphics.Point size = new android.graphics.Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Size screenSize = new Size(size.x, size.y);

        mKCFTrackerCountingSolution = new KCFTrackerCountingSolution(screenSize);

        mRgb = null;
    }

    public void onCameraViewStopped() {
        Log.d(TAG, "onCameraViewStopped");

        mRgb.release();
    }


    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Log.v(TAG, "onCameraFrame");
        //FIXME not loading properly
        mKCFTrackerCountingSolution.process(inputFrame.rgba());
        mRgb = mKCFTrackerCountingSolution.getPreviewMat(true);
        return mRgb;
    }

    public void setCurrLoc(String location) {
        currLoc = location;
    }
}
