package com.guozaiss.news;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.StrictMode;

import com.bumptech.glide.request.RequestListener;
import com.guozaiss.news.core.utils.LogUtils;
import com.guozaiss.news.core.utils.crash.CustomCrash;
import com.guozaiss.news.core.utils.imageLoad.GlideUtils;
import com.guozaiss.news.core.utils.imageLoad.ImageLoadUtils;
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

        if ((getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0) {
            LogUtils.e("当前处于debug模式。。。");
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()//严苟模式--线程
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()//严苟模式--虚拟机
                    .detectLeakedSqlLiteObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
//            refWatcher = LeakCanary.install(this);//内存泄漏检测
        }
    }

}
