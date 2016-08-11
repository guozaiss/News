package com.guozaiss.news.core.utils.imageLoad;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by guozaiss on 16/6/14.
 * <meta-data
 * android:name="com.guozaiss.news.common.utils.imageLoad.CustomCachingGlideModule"
 * android:value="GlideModule" />
 */
public class CustomCachingGlideModule implements GlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        //设置磁盘缓存的位置 大小
        builder.setDiskCache(
                new InternalCacheDiskCacheFactory(context, "glide", 50 * 1024 * 1024));
        //设置外置磁盘缓存的位置 大小
        builder.setDiskCache(
                new ExternalCacheDiskCacheFactory(context, "glide", 100 * 1024 * 1024));
        // In case you want to specify a cache folder ("glide"):
        builder.setDiskCache(
                new DiskLruCacheFactory(context.getCacheDir().getAbsolutePath(), "glide", 50 * 1024 * 1024));

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        //设置内存缓存大小
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        //设置缓存池
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        //设置图片解码方式
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // nothing to do here
    }
}