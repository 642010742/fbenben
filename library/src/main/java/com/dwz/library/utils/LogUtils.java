package com.dwz.library.utils;

import android.util.Log;

/**
 * 统一管理log
 */
public class LogUtils {

    public static String TAG = "LogUtils=========>";

    public static boolean DEBUG = true;

    public static void DEBUG(boolean open) {
        DEBUG = open;
    }

    public static void loge(String msg) {
        if (DEBUG)
            Log.e(TAG, msg);
    }

    public static void loge(String tag, String msg) {
        if (DEBUG)
            Log.e(tag, msg);
    }

    public static void logi(String msg) {
        if (DEBUG)
            Log.i(TAG, msg);
    }

    public static void logd(String msg) {
        if (DEBUG)
            Log.d(TAG, msg);
    }

    public static void logv(String msg) {
        if (DEBUG)
            Log.v(TAG, msg);
    }
}
