package com.guozaiss.news.common.utils.imageLoad;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestListener;
import com.guozaiss.news.R;

/**
 * Glide图片加载类
 * Created by guozaiss on 16/5/10.
 */
public class GlideUtils implements ImageLoadUtils<RequestListener> {

    @Override
    public void disPlay(Context context, ImageView imageView, String URL) {
        disPlay(context, imageView, URL, null);
    }

    @Override
    public void disPlay(Activity activity, ImageView imageView, String URL) {
        disPlay(activity, imageView, URL, null);
    }

    @Override
    public void disPlay(FragmentActivity fragmentActivity, ImageView imageView, String URL) {
        disPlay(fragmentActivity, imageView, URL, null);
    }

    @Override
    public void disPlay(Fragment fragment, ImageView imageView, String URL) {
        disPlay(fragment, imageView, URL, null);
    }

    @Override
    public void disPlay(android.support.v4.app.Fragment fragment, ImageView imageView, String URL) {
        disPlay(fragment, imageView, URL, null);
    }

    @Override
    public void disPlay(Context context, ImageView imageView, String URL, RequestListener requestListener) {
        Glide.with(context).load(URL).listener(requestListener).error(R.drawable.load_error).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }

    @Override
    public void disPlay(Activity activity, ImageView imageView, String URL, RequestListener requestListener) {
        Glide.with(activity).load(URL).listener(requestListener).error(R.drawable.load_error).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }

    @Override
    public void disPlay(FragmentActivity fragmentActivity, ImageView imageView, String URL, RequestListener requestListener) {
        Glide.with(fragmentActivity).load(URL).listener(requestListener).error(R.drawable.load_error).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }

    @Override
    public void disPlay(Fragment fragment, ImageView imageView, String URL, RequestListener requestListener) {
        Glide.with(fragment).load(URL).listener(requestListener).error(R.drawable.load_error).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }

    @Override
    public void disPlay(android.support.v4.app.Fragment fragment, ImageView imageView, String URL, RequestListener requestListener) {
        Glide.with(fragment).load(URL).listener(requestListener).error(R.drawable.load_error).diskCacheStrategy(DiskCacheStrategy.ALL).crossFade().into(imageView);
    }
}
