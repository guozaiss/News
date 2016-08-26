package com.guozaiss.news;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.bumptech.glide.request.target.ViewTarget;
import com.guozaiss.news.APIService.HttpHelper;
import com.guozaiss.news.core.FakeCrashLibrary;
import com.guozaiss.news.utils.LogUtils;
import com.guozaiss.news.utils.crash.CustomCrash;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class NewsApplication extends Application {
    private static NewsApplication instance;//上下文

    public static NewsApplication getApplicationInstance() {
        return instance;
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

        //init
        HttpHelper.initialize();
        //Glide BUG 修复
        ViewTarget.setTagId(R.id.glide_tag);
        //日志管理
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @Override
                protected String createStackElementTag(StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
        } else {
            Timber.plant(new CrashReportingTree());
        }

        if (BuildConfig.DEBUG) {
            LogUtils.e("当前处于debug模式。。。");
//            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()//严苟模式--线程
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()
//                    .penaltyLog()
//                    .build());
//            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()//严苟模式--虚拟机
//                    .detectLeakedSqlLiteObjects()
//                    .penaltyLog()
//                    .penaltyDeath()
//                    .build());
//            refWatcher = LeakCanary.install(this);//内存泄漏检测
        } else {
            mCustomCrash.setCustomCrashInfo(this);//启动崩溃日志收集程序，开发模式不开放
        }
    }

    private static class CrashReportingTree extends Timber.Tree {
        @Override
        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority == Log.VERBOSE || priority == Log.DEBUG) {
                return;
            }

            FakeCrashLibrary.log(priority, tag, message);

            if (t != null) {
                if (priority == Log.ERROR) {
                    FakeCrashLibrary.logError(t);
                } else if (priority == Log.WARN) {
                    FakeCrashLibrary.logWarning(t);
                }
            }
        }
    }
}
