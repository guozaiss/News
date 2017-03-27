package com.guozaiss.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bruce on 2017/2/10.
 */
public class BaseUIHelper {
    /**
     * 打开指定页面
     *
     * @param context
     * @param clz
     */
    public static void gotoActivity(Context context, Class<?> clz) {
        Intent intent = new Intent(context, clz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开指定页面
     *
     * @param context
     * @param clz
     * @param bundle
     */
    public static void gotoActivity(Context context, Class<?> clz, Bundle bundle) {
        Intent intent = new Intent(context, clz);
        intent.putExtras(bundle);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    /**
     * 打开指定页面，并返回应答数据
     *
     * @param activity
     * @param clz
     * @param requestCode
     */
    public static void gotoActivityForResult(Activity activity, Class<?> clz, int requestCode) {
        Intent intent = new Intent(activity, clz);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 打开指定页面，并返回应答数据
     *
     * @param activity
     * @param clz
     * @param requestCode
     * @param bundle
     */
    public static void gotoActivityForResult(Activity activity, Class<?> clz, int requestCode, Bundle bundle) {
        Intent intent = new Intent(activity, clz);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent, requestCode);
    }
}