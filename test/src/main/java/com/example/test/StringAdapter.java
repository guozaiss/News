package com.example.test;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by guozaiss on 16/6/13.
 */
public class StringAdapter extends BaseAdapter {
    List<String> strings;
    Context context;

    public StringAdapter(List<String> strings, Context context) {
        this.strings = strings;
        this.context = context;
    }

    @Override
    public int getCount() {
        return strings.size();
    }

    @Override
    public Object getItem(int position) {
        return strings.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_string, null);
        }
        TextView text = (TextView) convertView.findViewById(R.id.text);
        text.setText(strings.get(position));
        return convertView;
    }
}
