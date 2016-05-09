package com.guozaiss.news.common.utils;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串操作类
 * Created by guozaiss on 16/2/15.
 */
public class StrUtils {

    /**
     * 将时间转化为特定格式的字符串
     *
     * @param date  转化的时间
     * @param style "yyyy/MM/dd"
     * @return
     */
    public static String formatDate(Date date, String style) {
        SimpleDateFormat format = new SimpleDateFormat(style);
        return format.format(date);
    }

    /**
     * 判断字符串是否为空
     *
     * @param string
     * @return
     */
    public static boolean isEmpty(String string) {
        if (null == string) {
            return true;
        }
        if (string.length() == 0) {
            return true;
        }
        return false;
    }

    /**
     * 获取手机信息
     *
     * @return
     */
    public static String getPhoneModel(Context context) {
        StringBuilder msg = new StringBuilder();
        TelephonyManager phoneMgr = (TelephonyManager) ActivityManager.getInstance().peekActivity().getSystemService(Context.TELEPHONY_SERVICE);
        return msg.append("手机型号：" + Build.MODEL + "\n")
                .append("SystemVersion：" + Build.VERSION.RELEASE + "\n")
                .append("SDK版本号：" + Build.VERSION.SDK + "\n")
                .append("品牌：" + Build.BRAND + "\n")
                .append("设备：" + Build.DEVICE + "\n")
                .append("CPU：" + Build.CPU_ABI + "\n")
                .append("手机号：" + phoneMgr.getLine1Number() + "\n")
                .append("设备ID：" + phoneMgr.getDeviceId() + "\n")
                .toString();
    }
}
