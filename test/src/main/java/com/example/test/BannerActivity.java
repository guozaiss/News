package com.example.test;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class BannerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner);
        Banner banner = (Banner) findViewById(R.id.banner);
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        });
        //设置图片集合
        List<String> images = new ArrayList<>();
        images.add("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=2376684129,460330055&fm=58&s=98225E3285305C21006D98C60200D0B2&bpow=121&bpoh=75");
        images.add("https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=1089045665,2402486651&fm=80&w=179&h=119&img.JPEG");
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }
}
