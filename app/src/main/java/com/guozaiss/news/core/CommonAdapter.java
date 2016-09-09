package com.guozaiss.news.core;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by bruce on 16/5/9.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {

    protected Context context;
    protected List<T> lists;
    protected LayoutInflater mInflater;
    private int mLayoutId;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
        this.lists = datas;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public T getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = ViewHolder.get(context, convertView, parent,
                mLayoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T t);

    public void setData(List<T> mDatas) {
        this.lists = mDatas;
    }

    public List<T> getData() {
        return lists;
    }
}
