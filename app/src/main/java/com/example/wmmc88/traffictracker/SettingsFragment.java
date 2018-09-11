package com.example.wmmc88.traffictracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.util.Log;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    private static final String TAG = SettingsActivity.class.getSimpleName();

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        Log.d(TAG, "onCreatePreferences");

        addPreferencesFromResource(R.xml.preferences);
        updateAllPreferenceSummaries();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    private void updateAllPreferenceSummaries() {
        Log.d(TAG, "updateAllPreferenceSummaries");

        PreferenceScreen preferenceScreen = getPreferenceScreen();
        SharedPreferences sharedPreferences = preferenceScreen.getSharedPreferences();

        int numPreferences = preferenceScreen.getPreferenceCount();
        for (int i = 0; i < numPreferences; i++) {
            Preference preference = preferenceScreen.getPreference(i);

            updatePreferenceSummary(sharedPreferences, preference);
        }
    }

    private void updatePreferenceSummary(SharedPreferences sharedPreferences, Preference preference){
        Log.d(TAG, "updatePreferenceSummary");

        String preferenceKey = preference.getKey();

        if (preference instanceof EditTextPreference) {
            String preferenceValue = sharedPreferences.getString(preferenceKey,null);
            EditTextPreference editTextPreference = (EditTextPreference) preference;
            editTextPreference.setSummary(preferenceValue);
        }
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        Log.d(TAG, "onSharedPreferenceChanged");

        updatePreferenceSummary(sharedPreferences, findPreference(s));
    }

}
