package com.guozaiss.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by guozaiss on 16/2/15.
 */
public class SPUtils {

    private static SharedPreferences sp;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return sp;
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).apply();
    }

    public static String getString(Context context, String key) {
        return getSharedPreferences(context).getString(key, "");
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).apply();
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        return getSharedPreferences(context).getBoolean(key, defaultValue);
    }

    public static void putDouble(Context context, String key, double value) {
        getSharedPreferences(context).edit().putString(key, String.valueOf(value)).apply();
    }

    public static double getDouble(Context context, String key, double defaultValue) {
        return Double.parseDouble(getSharedPreferences(context).getString(key, String.valueOf(defaultValue)));
    }

    public static boolean exists(Context context, String key) {
        return getSharedPreferences(context).contains(key);
    }

}
