package com.example.test.slidedelete;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.test.R;

import java.util.ArrayList;
import java.util.List;

public class SlideDelete2Activity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_delete2_activity);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_slide_delete2ctivity);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        List<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            integers.add(i);
        }
        SlideDeleteAdapter adapter = new SlideDeleteAdapter(this, integers);
        recyclerView.setAdapter(adapter);
    }
}