package com.guozaiss.news.core;

/**
 * Created by bruce on 16/5/9.
 */
public class FakeCrashLibrary {

    public static void log(int priority, String tag, String message) {

    }

    public static void logWarning(Throwable t) {
    }

    public static void logError(Throwable t) {
    }

    private FakeCrashLibrary() {
        throw new AssertionError("No instances.");
    }

}

