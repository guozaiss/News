package com.example.test.slidedelete;

import android.content.Context;

import com.example.test.base.BaseRecyclerAdapter;
import com.example.test.base.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2017/2/14.
 */
public class SlideDeleteAdapter extends BaseRecyclerAdapter<Integer> {
    public SlideDeleteAdapter(Context ctx,List<Integer> integers) {
        super(ctx);
        mData = new ArrayList<>();
        mData.addAll(integers);
    }

    @Override
    public int getItemLayoutId(int viewType) {
        return android.R.layout.simple_list_item_1;
    }

    @Override
    public void bindData(RecyclerViewHolder holder, int position, Integer item) {
        holder.setText(android.R.id.text1, item + "");
    }
}