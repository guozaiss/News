package com.guozaiss.news.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.guozaiss.news.R;
import com.guozaiss.news.activity.HtmlActivity;
import com.guozaiss.news.core.CommonAdapter;
import com.guozaiss.news.core.ViewHolder;
import com.guozaiss.news.reptile.ZhiHuModel;
import com.guozaiss.news.utils.BaseUIHelper;

import java.util.List;

/**
 * Created by Admin on 2017/3/27.
 */
public class ZhiHuAdapter extends CommonAdapter<ZhiHuModel> {
    public ZhiHuAdapter(Context context, List<ZhiHuModel> datas) {
        super(context, R.layout.item_zhihu, datas);
    }

    @Override
    public void convert(ViewHolder holder, final ZhiHuModel zhiHuModel) {
        holder.setText(R.id.txt_title, zhiHuModel.getTitle());
        holder.setText(R.id.txt_content, zhiHuModel.getDescription());
        holder.setText(R.id.txt_auther, zhiHuModel.getCreator());
        holder.setText(R.id.txt_date, zhiHuModel.getPubdate());
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("url", zhiHuModel.getLink());
                BaseUIHelper.gotoActivity(context, HtmlActivity.class, bundle);
            }
        });
    }
}