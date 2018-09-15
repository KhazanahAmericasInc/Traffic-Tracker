package com.example.wmmc88.traffictracker;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
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

    private CameraBridgeViewBase mOpenCvCameraView;
    private Mat mRgb;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mPreviewFrameWidth;
    private int mPreviewFrameHeight;
    private String currLoc;


    // Location Services
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String locationProvider;


    private KCFTrackerCountingSolution mKCFTrackerCountingSolution;

    JavaCameraView javaCameraView;

    // Temporary frame counter for location services
    private int frames = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cam_activity);

        // Initialize java camera view (opencv), respective layouts and set configs
        javaCameraView = (JavaCameraView) findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().startUploadThread();
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

        // Prepare Looper for Location Updates
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        Toast.makeText(this, width + "x" + height, Toast.LENGTH_LONG).show();

        android.graphics.Point size = new android.graphics.Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        Size screenSize = new Size(size.x, size.y);

        mKCFTrackerCountingSolution = new KCFTrackerCountingSolution(screenSize);

        // ANDROID LOCATION MANAGER //

        // Initialize Location Manager
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // Abstract implementation of Location Listener
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(android.location.Location location) {
                // Log location updates

                Log.d(TAG, "LOCATION CHANGED: " + location.toString());
                mKCFTrackerCountingSolution.setCurrLoc(location.toString());
                currLoc = location.toString();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {}

            @Override
            public void onProviderEnabled(String provider) {}

            @Override
            public void onProviderDisabled(String provider) {}
        };

        // Set location to GPS location instead of service provider (i.e.Google Play location).
        locationProvider = LocationManager.GPS_PROVIDER;

        // Check location permissions
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

            } else {
                // do request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 8);
            }
        }

        // start location listener on time interval
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                0, locationListener);

        mRgb = null;
    }

    public void onCameraViewStopped() {
        Log.d(TAG, "onCameraViewStopped");

        mRgb.release();
    }



    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        Log.v(TAG, "onCameraFrame");
        mKCFTrackerCountingSolution.process(inputFrame.rgba());
        mRgb = mKCFTrackerCountingSolution.getPreviewMat(true);

        if(frames % 5 == 0) {
            // Check location permissions
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)) {
                } else {
                    // do request the permission
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 8);
                }
            }

            String lastKnownLocation = locationManager.getLastKnownLocation(locationProvider).toString().substring(0,34) + "]";

            mKCFTrackerCountingSolution.setCurrLoc(lastKnownLocation);
            CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().setLoc(lastKnownLocation);

        }
        frames++;


        return mRgb;
    }
}
