package com.guozaiss.news.common.utils.imageLoad;

import android.widget.ImageView;

/**
 * ImageLoadUtils 加载父类
 * Created by guozaiss on 16/5/11.
 */
public interface ImageLoadUtils<T> {
    /**
     * 加载图片Method
     *
     * @param object    可以为Context、Activity、Fragment（&V4）、FragmentActivity
     * @param imageView ImageView
     * @param URL       加载图片的URL
     */
    void disPlay(Object object, ImageView imageView, String URL);

    /**
     * 加载图片Method
     *
     * @param object    可以为Context、Activity、Fragment（&V4）、FragmentActivity
     * @param imageView ImageView
     * @param URL       加载图片的URL
     * @param t         加载图片监听
     */
    void disPlay(Object object, ImageView imageView, String URL, T t);

}
