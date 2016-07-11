package com.guozaiss.news;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.request.RequestListener;
import com.guozaiss.news.common.utils.LogUtils;
import com.guozaiss.news.common.utils.crash.CustomCrash;
import com.guozaiss.news.common.utils.imageLoad.GlideUtils;
import com.guozaiss.news.common.utils.imageLoad.ImageLoadUtils;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class NewsApplication extends Application {
    private static NewsApplication instance;//上下文

    //图片加载工具类
    static class ImageLoadUtilsHolder {
        private static ImageLoadUtils<RequestListener> imageLoadUtils = new GlideUtils();
    }

    public static NewsApplication getApplicationInstance() {
        return instance;
    }

    public static ImageLoadUtils<RequestListener> getImageLoadUtils() {
        return ImageLoadUtilsHolder.imageLoadUtils;
    }


    public static RefWatcher getRefWatcher(Context context) {
        NewsApplication application = (NewsApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        this.instance = this;
        //崩溃处理
        CustomCrash mCustomCrash = CustomCrash.getInstance();//初始化崩溃日志收集器
//        mCustomCrash.setCustomCrashInfo(this);//启动崩溃日志收集程序，开发模式不开放

        if (BuildConfig.debug) {
            LogUtils.e("当前处于debug模式。。。");
//            refWatcher = LeakCanary.install(this);
        }
    }

}
