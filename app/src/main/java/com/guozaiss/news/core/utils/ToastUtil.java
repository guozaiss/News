package com.guozaiss.news.core.utils;

import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Toast;

import com.guozaiss.news.NewsApplication;

/**
 * Created by guozaiss on 16/2/6.
 */
public class ToastUtil {
    private static Toast toast;

    /**
     * 获得单例 Toast
     *
     * @param msg
     * @param type
     * @return
     */
    private static Toast getToast(String msg, int type) {
        if (toast == null) {
            synchronized (ToastUtil.class) {
                if (toast == null) {
                    toast = Toast.makeText(NewsApplication.getApplicationInstance(), msg, type);
                    toast.setGravity(Gravity.BOTTOM, 0, 450);
                }
            }
        } else {
            toast.setText(msg);
            toast.setDuration(type);
        }
        return toast;
    }

    /**
     * 显示Toast
     *
     * @param msg  显示信息
     * @param type 显示时间
     */
    private static void showToast(String msg, int type) {
        getToast(msg, type).show();
        Animation translateIn = new TranslateAnimation(0, 0, -150, 150);
        translateIn.setDuration(10);
        translateIn.setFillAfter(true);
        toast.getView().startAnimation(translateIn);
    }

    /**
     * 显示土司 LENGTH_LONG
     *
     * @param msg
     */
    public static void showToastOfLong(String msg) {
        showToast(msg, Toast.LENGTH_LONG);
    }

    /**
     * 显示土司 LENGTH_SHORT
     *
     * @param msg
     */
    public static void showToastOfShort(String msg) {
        showToast(msg, Toast.LENGTH_SHORT);
    }

}
