package com.guozaiss.news.adapters;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.guozaiss.news.R;
import com.guozaiss.news.beans.News;
import com.guozaiss.news.core.CommonAdapter;
import com.guozaiss.news.core.ViewHolder;
import com.guozaiss.news.utils.GlideUtils;

import java.util.List;

/**
 * Created by Lenovo on 2016/7/10.
 */
public class NewTopAdapter extends CommonAdapter<News.ResultBean.DataBean> {
    public NewTopAdapter(Activity context, List<News.ResultBean.DataBean> lists) {
        super(context,R.layout.item_news, lists );
    }

    @Override
    public void convert(ViewHolder holder, News.ResultBean.DataBean dataBean) {
        ImageView imageView = holder.getView(R.id.imageView);
        holder.setText( R.id.title,dataBean.getTitle() + "");
        holder.setText(R.id.author,String.format("来源：%s", dataBean.getAuthor_name()));
        holder.setText(R.id.date,Html.fromHtml(String.format("更新时间：%s", "<font color=red>" + dataBean.getDate()) + "</font>"));
        if (TextUtils.isEmpty(dataBean.getThumbnail_pic_s())) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            GlideUtils.loadRoundedImage(context,dataBean.getThumbnail_pic_s(),0,imageView);
        }
    }
}
