package com.example.wmmc88.traffictracker;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.location.LocationManager;
import android.location.LocationListener;
import com.cloudrail.si.types.Location;

import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int VIDEO_LAUNCH = 0;
    private static final int CAMERA_LAUNCH = 1;
    private TextView mCurrentSettingsTextView;
    private int mLaunchMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);

        // SET DEFAULT CONTENT VIEW
        setContentView(R.layout.activity_main);

        // SET TEXT VIEW SETTINGS
        mCurrentSettingsTextView = findViewById(R.id.tv_current_settings);

        // INITIALIZE BUTTONS
        Button mLaunchButton = findViewById(R.id.b_launch);
        Button mConfigureSettingsButton = findViewById(R.id.b_configure_settings);

        // SET BUTTON ONCLICK LISTENERS
        mLaunchButton.setOnClickListener(this);
        mConfigureSettingsButton.setOnClickListener(this);

        setupSettingsTextView();

        // LAUNCH THE LAST SET CAMERA SELECTION
        mLaunchMode = Integer.parseInt(PreferenceManager.getDefaultSharedPreferences(this).getString(getString(R.string.pref_key_camera_selection), null));

        // INITIALIZE CLOUD RAIL
        CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().init(getApplicationContext());

    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();

        // SAVE CAMERA SETTINGS
        PreferenceManager.getDefaultSharedPreferences(MainActivity.this).unregisterOnSharedPreferenceChangeListener(this);

        CloudRailsUnifiedCloudStorageAPIUtils.getStaticInstance().stopUploadThread();
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "onClick");


        int clickViewId = view.getId();
        switch (clickViewId) {
            case R.id.b_launch:
                Log.v(TAG, "b_launch");
                // WORKING HERE SEPT 10 EOD
                Log.d(TAG, "THE LAUNCH MODE IS: " + Integer.toString(mLaunchMode));
                switch (mLaunchMode) {
                    case VIDEO_LAUNCH:

                        Intent intentToStartVideoMode = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(intentToStartVideoMode);
                        break;

                    case CAMERA_LAUNCH:
                        Intent intentToStartCameraMode = new Intent(MainActivity.this, CameraActivity.class);
                        startActivity(intentToStartCameraMode);
                        break;

                    default:
                        Log.e(TAG, "INVALID LAUNCH MODE");
                        break;
                }
                break;

            case R.id.b_configure_settings:
                Log.v(TAG, "b_configure_settings");
                Intent intentToOpenSettings = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intentToOpenSettings);
                break;
        }
    }

    private void setupSettingsTextView() {
        Log.d(TAG, "setupSettingsTextView");
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        updateSettingsTextView(sharedPreferences);
        sharedPreferences.registerOnSharedPreferenceChangeListener(MainActivity.this);
    }

    private void updateSettingsTextView(SharedPreferences sharedPreferences) {
        Log.d(TAG, "updateSettingsTextView");

        Map<String, ?> settings = sharedPreferences.getAll();

        String settingsString = "";
        for (Map.Entry<String, ?> setting : settings.entrySet()) {
            settingsString += setting.getKey() + ": \t" + setting.getValue() + '\n';
        }
        mCurrentSettingsTextView.setText(settingsString);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(TAG, "onSharedPreferenceChanged");
        updateSettingsTextView(sharedPreferences);
        mLaunchMode = Integer.parseInt(sharedPreferences.getString(getString(R.string.pref_key_camera_selection), null));
    }
}
