package com.guozaiss.news.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.guozaiss.news.R;
import com.guozaiss.news.activity.PictureActivity;
import com.guozaiss.news.core.CommonAdapter;
import com.guozaiss.news.core.ViewHolder;
import com.guozaiss.news.reptile.PictureModel;
import com.guozaiss.news.utils.BaseUIHelper;
import com.guozaiss.news.utils.GlideUtils;

import java.util.List;

/**
 * Created by Admin on 2017/3/24.
 */
public class PicAdapter extends CommonAdapter<PictureModel> {

    public PicAdapter(Context context, List<PictureModel> datas) {
        super(context, R.layout.item_pic, datas);
    }

    @Override
    public void convert(ViewHolder holder, final PictureModel pictureModel) {
        final ImageView img = holder.getView(R.id.img);
        GlideUtils.loadRoundedImage(context, pictureModel.getUrl(),20, img);
        holder.setText(R.id.txt_title, pictureModel.getTitle() + "");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", pictureModel.getUrl());
                BaseUIHelper.gotoActivity(context,PictureActivity.class,bundle);
            }
        });
    }
}