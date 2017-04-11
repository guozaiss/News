package com.example.test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;


public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        final ImageView iv_main = (ImageView) findViewById(R.id.iv_main);

        final ObjectAnimator anim = ObjectAnimator.ofFloat(iv_main, "rotation", 0, 359);
        anim.setDuration(800);
        anim.setRepeatCount(Integer.MAX_VALUE);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        anim.start();

        Glide.with(this)
                .load("http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0D/01/ChMkJlgq0z-IC78PAA1UbwykJUgAAXxIwMAwQcADVSH340.jpg")
                .crossFade()
                .dontAnimate()
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(final GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                        if (resource != null) {
                            anim.cancel();
                            iv_main.setRotation(0);
                            iv_main.setPadding(0, 0, 0, 0);
                            iv_main.setImageDrawable(resource);
                        }
                    }
                });
    }
}