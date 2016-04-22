package com.lew.mapleleaf.utils.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lew.mapleleaf.ui.MapleLeafApplication;

public class SPUtils implements OnSharedPreferenceChangeListener {

    private static final String PREFS_NAME = "zjh_pres";
    private static Map<String, SPUtils> instanceMap = new HashMap<String, SPUtils>();

    private SharedPreferences prefs;
    private List<PrefsChangedListener> prefsListeners = new ArrayList<>();

    public static interface PrefsChangedListener {
        public void onPrefsChanged(SharedPreferences prefs, String key);
    }

    public static SPUtils getInstance() {
        if (!instanceMap.containsKey(PREFS_NAME)) {
            SPUtils userInstance = new SPUtils(PREFS_NAME);
            instanceMap.put(PREFS_NAME, userInstance);
        }
        return instanceMap.get(PREFS_NAME);
    }

    private SPUtils(String filename) {
		prefs = MapleLeafApplication.getInstance().getSharedPreferences(filename, Context.MODE_PRIVATE);
		prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        for (PrefsChangedListener listener : prefsListeners) {
            if (listener == null) {
                continue;
            }
            listener.onPrefsChanged(prefs, key);
        }
    }

    public void close() {
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void putBoolean(String key, boolean value) {
        Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return prefs.getBoolean(key, defValue);
    }

    public <T> T getObject(String key, Class<T> clazz) {
        String jsonStr = prefs.getString(key, "");
        try {
            if (!TextUtils.isEmpty(jsonStr)) {
                T t = new Gson().fromJson(jsonStr, clazz);
                return t;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public void putObject(String key, Object value) {
        try {
            String jsonStr = new Gson().toJson(value);
            Editor editor = prefs.edit();
            editor.putString(key, jsonStr);
            editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String defValue) {
        return prefs.getString(key, defValue);
    }

    public void putString(String key, String value) {
        Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public int getInt(String key, int defValue) {
        return prefs.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void remove(String key) {
        Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

    public void addPrefsChangedListener(PrefsChangedListener listener) {
        prefsListeners.add(listener);
    }

    public void removePrefsChangedListener(PrefsChangedListener listener) {
        prefsListeners.remove(listener);
    }
}
