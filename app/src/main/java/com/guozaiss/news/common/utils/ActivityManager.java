package com.guozaiss.news.common.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by guozaiss on 16/2/15.
 */
public class ActivityManager {
    private Stack<Activity> activityStack = new Stack<>();


    private ActivityManager() {
    }

    /**
     * 获取管理者单例
     *
     * @return
     */
    public static ActivityManager getInstance() {
        return ManagerActivityHolder.instance;
    }

    static class ManagerActivityHolder {
        private static ActivityManager instance = new ActivityManager();
    }

    /**
     * 弹出栈顶元素
     *
     * @return
     */
    public Activity popActivity() {
        return activityStack.pop();
    }

    /**
     * 去除栈顶元素
     *
     * @return
     */
    public Activity peekActivity() {
        return activityStack.peek();
    }

    /**
     * 入栈
     *
     * @param mAcitvity
     */
    public void pushActivity(Activity mAcitvity) {
        activityStack.push(mAcitvity);
    }

    /**
     * 显示所有的activity
     */
    private void showActivity() {
        StringBuffer buffer = new StringBuffer();
        for (Activity activity :
                activityStack) {
            buffer.append("Activity:" + activity.getClass().getSimpleName() + "\n");
        }
        LogUtils.e(buffer.toString());
    }

    /**
     * 获得栈activity个数
     *
     * @return
     */
    public int size() {
        return activityStack.size();
    }

    /**
     * 弹出所有Activity并退出
     */
    public void finish() {
        for (Activity activity : activityStack) {
            LogUtils.e(activity.getClass().getSimpleName() + "退出了");
            activity.finish();
        }
        System.exit(0);
    }
}
