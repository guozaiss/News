package com.guozaiss.news.adapters;

import android.app.Activity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.guozaiss.news.R;
import com.guozaiss.news.beans.News;
import com.guozaiss.news.core.base.BaseAdapterE;
import com.guozaiss.news.core.base.BaseViewHolder;
import com.guozaiss.news.utils.CommonUtils;
import com.guozaiss.news.utils.imageLoad.PicassoUtils;

import java.util.List;

/**
 * Created by Lenovo on 2016/7/10.
 */
public class NewTopAdapter extends BaseAdapterE<News.ResultBean.DataBean> {
    public NewTopAdapter(Activity context, List<News.ResultBean.DataBean> lists, int xmlID) {
        super(context, lists, xmlID);
    }

    @Override
    protected void bindView(View convertView, int position) {
        TextView title = BaseViewHolder.get(convertView, R.id.title);
        ImageView imageView = BaseViewHolder.get(convertView, R.id.imageView);
        TextView author = BaseViewHolder.get(convertView, R.id.author);
        TextView date = BaseViewHolder.get(convertView, R.id.date);
        News.ResultBean.DataBean dataBean = lists.get(position);
        title.setText(dataBean.getTitle() + "");
        author.setText(String.format("来源：%s", dataBean.getAuthor_name()));
        date.setText(Html.fromHtml(String.format("更新时间：%s", "<font color=red>" + dataBean.getDate()) + "</font>"));
        if (TextUtils.isEmpty(dataBean.getThumbnail_pic_s())) {
            imageView.setVisibility(View.GONE);
        } else {
            imageView.setVisibility(View.VISIBLE);
            PicassoUtils.disPlay(context, dataBean.getThumbnail_pic_s())
                    .resize(CommonUtils.dip2px(context, 300), CommonUtils.dip2px(context, 200))
                    .centerCrop().into(imageView);
        }
    }
}
