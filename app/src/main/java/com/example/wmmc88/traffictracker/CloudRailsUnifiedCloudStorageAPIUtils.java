package com.example.wmmc88.traffictracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.Pair;

import com.cloudrail.si.CloudRail;
import com.cloudrail.si.interfaces.CloudStorage;
import com.cloudrail.si.services.Box;

import org.opencv.android.Utils;
import org.opencv.core.Mat;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.concurrent.LinkedBlockingQueue;

class CloudRailsUnifiedCloudStorageAPIUtils implements Runnable {
    private static final String TAG = CloudRailsUnifiedCloudStorageAPIUtils.class.getSimpleName();

    private static final CloudRailsUnifiedCloudStorageAPIUtils STATIC_INSTANCE = new CloudRailsUnifiedCloudStorageAPIUtils();
    private final static String CLOUDRAIL_LICENSE_KEY = "5b7c544270a77f7d86dd3187";
    private static boolean mUploadThreadRunning = false;
    final LinkedBlockingQueue<Pair<Mat, ExitDirection>> UPLOAD_QUEUE = new LinkedBlockingQueue<Pair<Mat, ExitDirection>>();
    private CloudStorage CLOUD_STORAGE = null;
    private Thread mUploadThread = null;

    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    public static CloudRailsUnifiedCloudStorageAPIUtils getStaticInstance() {
        return STATIC_INSTANCE;
    }

    public void init(final Context context) {
        Log.i(TAG, "Initializing CloudRails CloudStorage API");
        new Thread(new Runnable() {
            @Override
            public void run() {
                login(context);
            }
        }, "Thread to Init CloudRails API").start();
    }

    private void login(Context context) {
        Log.i(TAG, "Login Attempted to Box");
        CloudRail.setAppKey(CLOUDRAIL_LICENSE_KEY);

        //** SHARED PREFS FOR SAVING SESSION **//
        sharedPref = context.getSharedPreferences("BOX", Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        //////

        this.CLOUD_STORAGE = new Box(context, "4nk737oaok582pg99ezt67zxctc9pfzo", "AtIDfTK4pXu2JsudSmp9wJYcODr2CvLi");

        String session = sharedPref.getString("SESSION_STRING", "");
        if(session != ""){
            try {
                this.CLOUD_STORAGE.loadAsString(session);
            }catch(Exception e){

            }
        }else {
            this.CLOUD_STORAGE.login();
        }

        //** SAVING LAST SESSION **//

        String result = this.CLOUD_STORAGE.saveAsString();

        // Save your string in SharedPref
        editor.putString("SESSION_STRING", result);
        editor.commit();

        //////////////////////////////
    }

    private String createNewFolder() {
        Log.i(TAG, "New Folder Created in Cloud Storage");
        String folderPath = "/" + Calendar.getInstance().getTime().toString();
        this.CLOUD_STORAGE.createFolder(folderPath);
        return folderPath;
    }

    public void startUploadThread() {
        mUploadThreadRunning = true;
        mUploadThread = new Thread(this, "CloudRails Upload Thread");
        mUploadThread.start();
    }

    public void stopUploadThread() {
        Log.d(TAG, "destroyProcessingThread");
        mUploadThreadRunning = false;

        //Try to join thread with UI thread
        boolean retry = true;
        while (retry) {
            try {
                Log.i(TAG, "Waiting for CloudRails Upload Thread to die");
                mUploadThread.join();
                retry = false;
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted Exception when waiting for CloudRails Upload thread to die");
            }
        }
    }

    @Override
    public void run() {

        String folderPath = this.createNewFolder();

        while (mUploadThreadRunning) {
            try {
                Pair p = UPLOAD_QUEUE.take();
                Log.i(TAG, "Took mat from Upload Queue");

                Mat matToUpload = (Mat) p.first;
                if (matToUpload != null) {
                    ExitDirection exitDirection = (ExitDirection) p.second;
                    String direction = null;
                    switch (exitDirection) {
                        case ZONE1:
                            direction = "ZONE1";
                            break;

                        case ZONE2:
                            direction = "ZONE2";
                            break;

                        case UNKNOWN:
                            direction = "UNKNOWN";
                            break;
                    }
                    String fileName = Calendar.getInstance().getTime() + direction + ".jpg";

                    Bitmap vehicleImage = Bitmap.createBitmap(matToUpload.cols(), matToUpload.rows(), Bitmap.Config.ARGB_8888);
                    Utils.matToBitmap(matToUpload, vehicleImage);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    vehicleImage.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

                    this.CLOUD_STORAGE.upload(folderPath + "/" + fileName, bais, baos.size(), false);
                }
            } catch (InterruptedException e) {
                Log.w(TAG, "Interrupted Exception when trying to take mat from Upload Queue");
                e.printStackTrace();
            }
        }
        Log.i(TAG, "CloudRails Upload Thread has finished");
    }
}
