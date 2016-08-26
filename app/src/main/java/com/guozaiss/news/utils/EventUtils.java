package com.guozaiss.news.utils;

import android.util.SparseArray;

/**
 * Created by bruce on 15/12/11.
 */
public class EventUtils {
    private static SparseArray<Long> lastClickTimeMap = new SparseArray<Long>();

    public static boolean isFastDoubleClick(int resId) {
        long cur = System.currentTimeMillis();
        long last = lastClickTimeMap.get(resId, cur - 600);
        if (cur - last < 500) {
            return true;
        }
        lastClickTimeMap.put(resId, cur);
        return false;
    }
}
