package com.example.wmmc88.traffictracker;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.location.LocationManager;
import android.location.LocationListener;

import com.cloudrail.si.types.Location;

import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class VideoActivity extends AppCompatActivity implements Runnable {
    public static final int EXTERNAL_STORAGE_PERMISSION_REQUEST = 2;
    private static final String TAG = VideoActivity.class.getSimpleName();

    static {
        if (!OpenCVLoader.initDebug()) {
            Log.e(TAG, "ERROR. COULD NOT LOAD STATIC OPENCV LIBRARIES!!!");
            //TODO Handler to finish activity if not loaded properly
        } else {
//            Other OpenCV JNI libs should be here:
//            System.loadLibrary("my_jni_lib1");
//            System.loadLibrary("my_jni_lib2");
        }

    }

    private Thread mProcessingThread = null;
    private volatile boolean mProcessingThreadRunning = false;
    private VideoSurfaceView mVideoSurfaceView;
    private KCFTrackerCountingSolution mKCFTrackerCountingSolution;
    private VideoCapture mVC;

    // LOCATION SERVICES
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String locationProvider;
    private String currLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        mVideoSurfaceView = findViewById(R.id.vsv);

        CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().startUploadThread();
    }


    @SuppressLint("InlinedApi")
    private boolean checkPermissionsGranted() {
        Log.d(TAG, "checkPermissionsGranted");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult");

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case EXTERNAL_STORAGE_PERMISSION_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "External Storage Permissions Granted!");
//                    mProcessingThreadRunning = true;
//                    mProcessingThread = new Thread(VideoActivity.this, "Processing Thread");
//                    mProcessingThread.start();
                } else {
                    Log.w(TAG, "Permissions Denied!");
                    this.finish();
                }
                return;
            }
            //other permission request cases
        }
    }


    @Override
    public void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (checkPermissionsGranted() == true) {
            if (loadVideo()) {
                mProcessingThreadRunning = true;
                mProcessingThread = new Thread(VideoActivity.this, "Processing Thread");
                mProcessingThread.start();
            } else {
                finish();
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_REQUEST);
        }

    }

    @Override
    public void onPause() {
        Log.d(TAG, "onPause");
        if (mProcessingThreadRunning) {
            destroyProcessingThread();
        }
        super.onPause();
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy");

        CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().stopUploadThread();

        super.onDestroy();
    }

    @Override
    public void run() {
        // Prepare Looper for Location Updates
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }

        Log.i(TAG, "Processing Thread Started");
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                //showRationale();
            } else {
                // do request the permission
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 8);
            }
        }





        Mat inputFrame = new Mat();
        Mat previewMat = null;

        while (mProcessingThreadRunning && mVC.read(inputFrame)) {
            Log.v(TAG, "new frame grabbed");

            Imgproc.cvtColor(inputFrame, inputFrame, Imgproc.COLOR_RGB2BGR);

            String locationProvider = LocationManager.GPS_PROVIDER;
            // Or use LocationManager.GPS_PROVIDER

            String lastKnownLocation = locationManager.getLastKnownLocation(locationProvider).toString();
            Log.d(TAG, "LOCATION IS NOW: " + lastKnownLocation);

            // start location listener on time interval
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
                    0, locationListener);
            mKCFTrackerCountingSolution.process(inputFrame);

            previewMat = mKCFTrackerCountingSolution.getPreviewMat(true);
            Bitmap bm = Bitmap.createBitmap(previewMat.cols(), previewMat.rows(), Bitmap.Config.ARGB_8888);
            Utils.matToBitmap(previewMat, bm);

            try {
                mVideoSurfaceView.FRAME_BUFFER.put(bm);
                Log.v(TAG, "frame sent to FRAME_BUFFER in SurfaceView Thread");
            } catch (InterruptedException e) {
                Log.e(TAG, "Interrupted while trying to add frame to buffer!");
                Log.e(TAG, e.getStackTrace().toString());
            }
            mVideoSurfaceView.mZone1Count.set(mKCFTrackerCountingSolution.mZone1Count);
            mVideoSurfaceView.mZone2Count.set(mKCFTrackerCountingSolution.mZone2Count);
            mVideoSurfaceView.mActiveTrackersCount.set(mKCFTrackerCountingSolution.getNumActiveTrackers());
            mVideoSurfaceView.mCurrentLocation = lastKnownLocation;

            mVideoSurfaceView.mReceivedFrameCount.incrementAndGet();
            Log.v(TAG, "Zone1: " + mKCFTrackerCountingSolution.mZone1Count + "\tZone2: " + mKCFTrackerCountingSolution.mZone2Count + "\tCurrent Trackers: " + mKCFTrackerCountingSolution.getNumActiveTrackers());
        }
        Log.i(TAG, "Processing Thread Finished");
        //TODO use handler finish activity from other thread
//        finish();
    }

    private boolean loadVideo() {
        Log.d(TAG, "loadVideo");
        //TODO select video file location using built in android app
        //TODO use decoder that converts to mjpg in avi container (only acceptable videotype in opencv 3.4.2)

        String filePath = "/storage/emulated/0/TrafficTracker/output.avi";
//        String filePath = "/storage/0000-0000/TrafficTracker/output.avi";
        mVC = new VideoCapture(filePath);
        if (mVC.isOpened()) {
            Log.i(TAG, "Video Opened!!");
            return true;
        } else {
            Log.e(TAG, "Video at " + filePath + " could not be opened!");

            VideoActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Toast.makeText(VideoActivity.this, "Video could not be opened!", Toast.LENGTH_LONG).show();
                }
            });
            return false;
        }
    }

    //call in on pause not on destroy
    private void destroyProcessingThread() {
        Log.d(TAG, "destroyProcessingThread");
        mProcessingThreadRunning = false;

        //Try to join thread with UI thread
        boolean retry = true;
        while (retry) {
            try {
                Log.i(TAG, "Waiting for Processing Thread to die");
                mProcessingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted Exception when waiting for processing thread to die");
            }
        }
    }

}


