package com.guozaiss.news.utils;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;

import com.guozaiss.news.R;

/**
 * Created by Admin on 2017/3/27.
 */
public class AnimUtils {
    public static void startAnimation(Context context, View view) {
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.rotate_refresh);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        view.startAnimation(operatingAnim);
    }
}