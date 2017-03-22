package com.guozaiss.news;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.bumptech.glide.request.target.ViewTarget;
import com.guozaiss.news.APIService.HttpHelper;
import com.guozaiss.news.core.FakeCrashLibrary;

import timber.log.Timber;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class NewsApplication extends Application {
    private static NewsApplication instance;//上下文

    public static NewsApplication getApplicationInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
//        //监听异常崩溃
//        Thread.setDefaultUncaughtExceptionHandler(AppException
//                .getAppExceptionHandler());
        //init Http
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
            Timber.d("当前处于debug模式。。。");
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
//           LeakCanary.install(this);//内存泄漏检测
        }
        //自动匹配主题
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO);
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
