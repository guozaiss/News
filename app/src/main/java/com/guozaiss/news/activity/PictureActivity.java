package com.guozaiss.news.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.guozaiss.news.R;
import com.guozaiss.news.core.base.view.BaseActivity;
import com.guozaiss.news.utils.GlideUtils;

import butterknife.BindView;

public class PictureActivity extends BaseActivity {

    @BindView(R.id.img)
    ImageView img;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);
        Intent intent = getIntent();
        if (intent != null) {
            url = intent.getStringExtra("url");
            GlideUtils.loadImage(this, url, img);
        }
    }
}