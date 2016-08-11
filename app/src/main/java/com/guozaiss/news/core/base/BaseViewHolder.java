package com.guozaiss.news.core.base;

import android.util.SparseArray;
import android.view.View;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class BaseViewHolder {
    /**
     * 复用 convertView 的封装
     *
     * @param view convertView
     * @param id   构建Item用到的布局文件中的一个资源id
     * @param <T>
     * @return View 实际上就是 convertView
     */
    public static <T extends View> T get(View view, int id) {

        SparseArray<View> viewHolder = (SparseArray<View>) view.getTag();
        if (viewHolder == null) {
            viewHolder = new SparseArray<>();
            view.setTag(viewHolder);
        }
        View childView = viewHolder.get(id);
        if (childView == null) {
            childView = view.findViewById(id);
            viewHolder.put(id, childView);
        }
        return (T) childView;
    }
}
