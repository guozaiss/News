package com.guozaiss.news.core.utils.imageLoad;

import android.content.Context;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

/**
 * Created by Lenovo on 2016/6/30.
 */
public class PicassoUtils {
    public static RequestCreator disPlay(Context context, String Url) {
        return Picasso.with(context)
                .load(Url)
//                .placeholder(R.mipmap.ic_launcher)//占位符
//                .error(R.mipmap.ic_launcher)//加载失败图片
                ;
    }
}
