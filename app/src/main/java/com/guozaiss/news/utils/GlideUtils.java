package com.guozaiss.news.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.guozaiss.news.R;

/**
 * Created by bruce on 16/7/21.
 */
public class GlideUtils {

    /**
     * 通过网络地址加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.grey_c5c5c5)
                .error(R.color.grey_c5c5c5)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
    }

    /**
     * 通过网络地址加载带圆角的图片
     *
     * @param context
     * @param imageUrl
     * @param radius
     * @param imageView
     */
    public static void loadRoundedImage(Context context, String imageUrl, int radius, ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.color.grey_c5c5c5)
                .error(R.color.grey_c5c5c5)
                .bitmapTransform(new CenterCrop(context), new RoundedCornersTransformation(context, radius, 0, RoundedCornersTransformation.CornerType.ALL))
                .dontAnimate()
                .into(imageView);
    }
}
