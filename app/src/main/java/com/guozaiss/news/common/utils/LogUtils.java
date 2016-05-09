package com.guozaiss.news.common.utils;

import android.util.Log;

import com.guozaiss.news.BuildConfig;

/**
 * Created by guozaiss on 16/2/15.
 */
public class LogUtils {
    private static boolean debug = BuildConfig.debug;//debug模式
    private static boolean detailed = false;

    /**
     * 错误级别日志
     *
     * @param msg
     */
    public static void e(String msg) {
        if (debug) {
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            int i = getI(stacks);
            location = "File：" + stacks[i].getFileName() +
                    "\nClass：" + stacks[i].getClassName() +
                    "\nMethod：" + stacks[i].getMethodName() +
                    "\nLineNumber：" + stacks[i].getLineNumber() +
                    "\nMessage：" + (msg == null ? "null" : msg);
            if (detailed) {
                Log.e(stacks[i].getFileName(), location);
            } else {
                Log.e(stacks[i].getFileName(), msg);
            }
        }
    }

    /**
     * 错误级别日志
     *
     * @param TAG
     * @param msg
     */
    public static void e(String TAG, String msg) {
        if (debug) {
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            int i = getI(stacks);
            location = "File：" + stacks[i].getFileName() +
                    "\nClass：" + stacks[i].getClassName() +
                    "\nMethod：" + stacks[i].getMethodName() +
                    "\nLineNumber：" + stacks[i].getLineNumber() +
                    "\nMessage：" + (msg == null ? "null" : msg);
            Log.e(TAG, msg);
        }
    }

    /**
     * debug级别日志
     *
     * @param msg
     */
    public static void d(String msg) {
        if (debug) {
            String location = "";
            StackTraceElement[] stacks = Thread.currentThread().getStackTrace();
            int i = getI(stacks);
            location = "File：" + stacks[i].getFileName() +
                    "\nClass：" + stacks[i].getClassName() +
                    "\nMethod：" + stacks[i].getMethodName() +
                    "\nLineNumber：" + stacks[i].getLineNumber() +
                    "\nMessage：" + (msg == null ? "null" : msg);
            if (detailed) {
                Log.d(stacks[i].getFileName(), location);
            } else {
                Log.d(stacks[i].getFileName(), msg);
            }
        }
    }


    /**
     * 获取调用该类 的类的相关信息的索引值
     *
     * @param stacks
     * @return
     */
    private static int getI(StackTraceElement[] stacks) {
        int i = 0;
        for (; i < stacks.length; i++) {
            if (stacks[i].getFileName().startsWith("LogUtils")) {
                i++;
                break;
            }
        }
        return i;
    }
}