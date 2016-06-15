package com.guozaiss.news.common.utils.imageLoad;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

/**
 * ImageLoadUtils 加载父类
 * Created by guozaiss on 16/5/11.
 */
public interface ImageLoadUtils<T> {

    /**
     * 加载图片Method
     *
     * @param context  可以为Context、Activity、Fragment（&V4）、FragmentActivity
     * @param imageView ImageView
     * @param URL       加载图片的URL
     */
    void disPlay(Context context, ImageView imageView, String URL);

    void disPlay(Activity activity, ImageView imageView, String URL);

    void disPlay(FragmentActivity fragmentActivity, ImageView imageView, String URL);

    void disPlay(Fragment fragment, ImageView imageView, String URL);

    void disPlay(android.support.v4.app.Fragment fragment, ImageView imageView, String URL);
    /**
     * 加载图片Method
     *
     * @param context    可以为Context、Activity、Fragment（&V4）、FragmentActivity
     * @param imageView ImageView
     * @param URL       加载图片的URL
     * @param t         加载图片监听
     */
    void disPlay(Context  context, ImageView imageView, String URL, T t);

    void disPlay(Activity activity, ImageView imageView, String URL, T t);

    void disPlay(FragmentActivity fragmentActivity, ImageView imageView, String URL, T t);

    void disPlay(Fragment fragment, ImageView imageView, String URL, T t);

    void disPlay(android.support.v4.app.Fragment fragment, ImageView imageView, String URL, T t);

}
