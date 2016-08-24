package com.guozaiss.news.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.readyidu.market.activities.RobotActivity;
import com.readyidu.market.activities.SimpleBackActivity;

/**
 * Created by bruce on 16/8/20.
 */
public class UIHelper {


    /**
     * 打开段子列表
     *
     * @param context
     */
    public static void showJokeList(Context context) {
        showSimpleBackPage(context, SimpleBackPage.jokeListAction);
    }

    /**
     * TODO:打开页面基础方法
     */

//    public static void showSimpleBackPage(Context context, SimpleBackPage page, ExtrasEntity entity) {
//        Bundle bundle = new Bundle();
//        bundle.putSerializable(AppConfigs.EXTRA_PARAMS, entity);
//        showSimpleBackPage(context, page, bundle);
//    }
    public static void showSimpleBackPage(Context context, SimpleBackPage page) {
        Bundle bundle = new Bundle();
        bundle.putInt(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        bundle.putBundle(SimpleBackActivity.BUNDLE_KEY_ARGS, null);
        gotoActivity(context, SimpleBackActivity.class, bundle);
    }


    public static void showSimpleBackPage(Context context, SimpleBackPage page, Bundle args) {
        Bundle bundle = new Bundle();
        bundle.putInt(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        bundle.putBundle(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        gotoActivity(context, SimpleBackActivity.class, bundle);
    }

    public static void showSimpleBackPageForResult(Context context, SimpleBackPage page, int requestCode, Bundle args) {
        Bundle bundle = new Bundle();
        bundle.putInt(SimpleBackActivity.BUNDLE_KEY_PAGE, page.getValue());
        bundle.putBundle(SimpleBackActivity.BUNDLE_KEY_ARGS, args);
        gotoActivityForResult((Activity) context, SimpleBackActivity.class, requestCode, bundle);
    }

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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivityForResult(intent, requestCode);
    }

}
