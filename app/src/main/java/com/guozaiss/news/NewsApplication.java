package com.guozaiss.news;

import android.app.Application;

import com.guozaiss.news.common.utils.crash.CustomCrash;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class NewsApplication extends Application {
    private static NewsApplication instance;

    public static NewsApplication getInstance() {
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //初始化崩溃日志收集器
        CustomCrash mCustomCrash = CustomCrash.getInstance();
        //启动崩溃日志收集程序，开发模式不开放
//        mCustomCrash.setCustomCrashInfo(this);
    }
}
