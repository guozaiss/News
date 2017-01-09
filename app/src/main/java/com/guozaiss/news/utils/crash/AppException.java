package com.guozaiss.news.utils.crash;

import timber.log.Timber;

/**
 * Created by bruce on 16/12/21.
 */
public class AppException extends Exception implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultHandler;

    private AppException() {
        this.mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static AppException getAppExceptionHandler() {
        return new AppException();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        if (!handleException(throwable) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, throwable);
        }
    }

    private boolean handleException(Throwable throwable) {
        if (throwable == null) {
            return false;
        }

        Timber.e(throwable.toString());
        return true;
    }
}
