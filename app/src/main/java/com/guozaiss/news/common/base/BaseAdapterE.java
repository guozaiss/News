package com.guozaiss.news.common.base;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by Lenovo on 2016/5/8.
 */
public abstract class BaseAdapterE<T> extends BaseAdapter {
    protected Activity context;//上下文
    protected List<T> lists;//集合
    private int xmlID;//item资源文件
    private LayoutInflater inflater = null;

    public BaseAdapterE(Activity context, List<T> lists, int xmlID) {
        this.context = context;
        this.lists = lists;
        this.xmlID = xmlID;
        inflater = LayoutInflater.from(context);
    }

    public void changeLists(List<T> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int size = 0;
        if (lists != null) {
            size = lists.size();
        }
        return   size;
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
        if (convertView == null) {
            convertView = inflater.inflate(xmlID, null);
        }
        bindView(convertView,position);
        return convertView;
    }
   abstract protected void bindView(View convertView,int position);
}
