package com.example.test;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    private View view;
    private ValueAnimator anim;
    private ValueAnimator anim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        view = findViewById(R.id.view);
        view.setOnClickListener(this);
        //属性动画
        anim = ObjectAnimator.ofFloat(view,"X",view.getX(), 500f);
        anim2 = ObjectAnimator.ofFloat(view,"Y",view.getY(), 500f);
        anim.setDuration(300);
        anim.setInterpolator(new LinearInterpolator());
        anim2.setDuration(300);
        anim2.setInterpolator(new LinearInterpolator());


        //普通动画
//        Interpolator interpolator = new Interpolator() {
//            @Override
//            public float getInterpolation(float input) {
//                return input * input * ((1.70158f + 1) * input - 1.70158f);
//            }
//        };
//        AnimationSet anim=new AnimationSet(true);
//        AlphaAnimation a=new AlphaAnimation(1,0.5f);
//        RotateAnimation ra=new RotateAnimation(0, 720, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f);
//        TranslateAnimation translateAnimation = new TranslateAnimation(0, 250f, 0, 250f);
//        anim.addAnimation(a);
//        anim.addAnimation(ra);
//        anim.addAnimation(translateAnimation);
//        anim.setDuration(3000);//历时时间
//        anim.setStartOffset(1000);//开始偏移时间
//        anim.setRepeatMode(Animation.RESTART);
//        anim.setInterpolator(new AccelerateInterpolator());
//        anim.setRepeatCount(5);
//        anim.setFillAfter(true);
//        anim.setFillBefore(false);
//        anim.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//        view.startAnimation(anim);

    }

    @Override
    public void onClick(View v) {
        anim.start();
        anim2.start();
        Toast.makeText(this, "点击", Toast.LENGTH_SHORT).show();
    }

}