package com.i.englishbook.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.i.englishbook.BuildConfig;

import java.util.Date;

/**
 * Created by huytran on 8/17/2017.
 */


public class PreferencesHelper {
    private static SharedPreferences sharedPrefer = null;

    public static SharedPreferences getInstance(Context c) {
        if (sharedPrefer == null)
            sharedPrefer = c.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        return sharedPrefer;
    }

    public static String getString(Context c, String key) {
        return getInstance(c).getString(key, "");
    }

    public static void setString(Context c, String key, String value) {
        SharedPreferences.Editor editor = getInstance(c).edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean getBool(Context c, String key) {
        boolean value = false;
        try {
            value = getInstance(c).getBoolean(key, false);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;

    }

    public static void setBool(Context c, String key, boolean value) {
        SharedPreferences.Editor editor = getInstance(c).edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static int getInt(Context c, String key, int defaultValue) {
        int value = 0;
        try {
            value = getInstance(c).getInt(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return value;
    }

    public static Date getDate(Context c, String key) {
        String value = getInstance(c).getString(key, "");
        if (CodeHelper.isEmptyOrNull(value)) return Common.MIN_DATE;
        return CodeHelper.getDate(value, Common.FORMAT_DATE);
    }

    public static void setDate(Context c, String key, Date value) {
        SharedPreferences.Editor editor = getInstance(c).edit();
        editor.putString(key, CodeHelper.getDate(value, Common.FORMAT_DATE));
        editor.apply();
    }

    public static void setInt(Context c, String key, int value) {
        SharedPreferences.Editor editor = getInstance(c).edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static float getFloat(Context c, String key, float defaultValue) {
        float value = 0;
        try {
            value = getInstance(c).getFloat(key, defaultValue);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return value;
    }

    public static void setFloat(Context c, String key, float value) {
        SharedPreferences.Editor editor = getInstance(c).edit();
        editor.putFloat(key, value);
        editor.apply();
    }


}