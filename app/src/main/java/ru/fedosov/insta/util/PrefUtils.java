package ru.fedosov.insta.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

public class PrefUtils {

    private static Context sContext;
    private static SharedPreferences sSettings = null;
    private static SharedPreferences.Editor sEditor = null;
    private static Gson sGson = new Gson();

    @SuppressLint("CommitPrefEdits")
    public static void init(Context context) {
        PrefUtils.sContext = context;
        sSettings = PreferenceManager.getDefaultSharedPreferences(context);
        sEditor = sSettings.edit();
    }


    public static void putString(int key, String value) {
        sEditor.putString(getKey(key), value).apply();
    }

    public static void putString(String keyName, String value) {
        sEditor.putString(keyName, value).apply();
    }

    public static String getString(int key) {
        return sSettings.getString(getKey(key), null);
    }

    public static void putInt(int key, Integer value) {
        sEditor.putInt(getKey(key), value).apply();
    }

    public static int getInt(int key) {
        return sSettings.getInt(getKey(key), 0);
    }

    public static int getInt(int key, int defValue) {
        return sSettings.getInt(getKey(key), defValue);
    }

    public static void putBoolean(int key, Boolean value) {
        sEditor.putBoolean(getKey(key), value).apply();
    }

    public static boolean getBoolean(int key) {
        return sSettings.getBoolean(getKey(key), false);
    }

    public static void putFloat(int key, Float value) {
        sEditor.putFloat(getKey(key), value).apply();
    }

    public static float getFloat(int key) {
        return sSettings.getFloat(getKey(key), 0);
    }

    public static void putLong(int key, long value) {
        sEditor.putLong(getKey(key), value).apply();
    }

    public static void putLongSync(int key, long value) {
        sEditor.putLong(getKey(key), value).commit();
    }

    public static long getLong(int key) {
        return sSettings.getLong(getKey(key), 0);
    }


    private static SharedPreferences getDefaultPrefs() {
        return PreferenceManager.getDefaultSharedPreferences(sContext);
    }

    public static String getKey(int key) {
        return sContext.getString(key);
    }

    public static boolean contains(int key) {
        return sSettings.contains(getKey(key));
    }

    public static void clear() {
        sEditor.clear().apply();
    }

    public static void remove(int key) {
        sEditor.remove(getKey(key)).commit();
    }

    public static void remove(String name) {
        sEditor.remove(name).commit();
    }

    public static void putBooleanSync(int key, boolean value) {
        sEditor.putBoolean(getKey(key), value).commit();
    }

    public static void putStringSync(int key, String value) {
        sEditor.putString(getKey(key), value).commit();
    }

    public static void putStringSync(String keyName, String value) {
        sEditor.putString(keyName, value).commit();
    }

    private static String getString(int key, String defaultValue) {
        return sSettings.getString(getKey(key), defaultValue);
    }

}
