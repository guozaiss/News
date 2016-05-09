package com.guozaiss.news.adapters;

import android.app.Activity;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guozaiss.news.R;
import com.guozaiss.news.common.base.BaseAdapterE;
import com.guozaiss.news.common.base.BaseViewHolder;
import com.guozaiss.news.entities.Data;

import java.util.List;

/**
 * Created by Lenovo on 2016/5/9.
 */
public class NewsAdapter extends BaseAdapterE<Data.Result> {
    public NewsAdapter(Activity context, List<Data.Result> lists, int xmlID) {
        super(context, lists, xmlID);
    }

    @Override
    protected void bindView(View convertView, int position) {
        TextView title = BaseViewHolder.get(convertView, R.id.title);
        TextView content = BaseViewHolder.get(convertView, R.id.content);
        TextView source = BaseViewHolder.get(convertView, R.id.source);
        TextView date = BaseViewHolder.get(convertView, R.id.date);
        TextView distance = BaseViewHolder.get(convertView, R.id.distance);
        ImageView imageView = BaseViewHolder.get(convertView, R.id.imageView);

        Data.Result result = lists.get(position);
        title.setText(result.getTitle() + "");
        content.setText(Html.fromHtml(result.getContent()) + "");
        source.setText(String.format("来源：%s", result.getSrc()));
        date.setText(Html.fromHtml(String.format("更新时间：%s", "<font color=red>" + result.getPdate_src()) + "</font>"));
        distance.setText(Html.fromHtml(String.format("距现在：%s", "<font color=blue>" + result.getPdate()) + "</font>"));
        Glide.with(context).load(result.getImg()).into(imageView);
    }
}
