package com.example.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ListView;

import java.util.ArrayList;

public class PtrActivity extends AppCompatActivity {
    private CollapsingToolbarLayoutState state;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private NestedScrollView nested;
    private CollapsingToolbarLayout collapsing_toolbar;
    private AppBarLayout app_bar;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ptr);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("主页面");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ListView listView2 = (ListView) findViewById(R.id.listView);
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            arrayList2.add("item2 " + i);
        }
        listView2.setAdapter(new StringAdapter(arrayList2, this));
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Title");
        collapsingToolbar.setTitleEnabled(false);//设置title不会动
        //通过CollapsingToolbarLayout修改字体颜色
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#888888"));//设置还没收缩时状态下字体颜色
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));//设置收缩后Toolbar上字体的颜色
        app_bar = (AppBarLayout) findViewById(R.id.appbar);
//        toolbar.getBackground().setAlpha(255);
        collapsing_toolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        nested = (NestedScrollView) findViewById(R.id.nested);
//        app_bar.setExpanded(false);
        app_bar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset == 0) {
                    if (state != CollapsingToolbarLayoutState.EXPANDED) {
                        state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    }
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                        state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                    }
                } else {
                    if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                        if (state == CollapsingToolbarLayoutState.COLLAPSED) {

                        }
                        state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间
                    }
                    float v = Math.abs((float) verticalOffset) / appBarLayout.getTotalScrollRange();
                    if (v < 1) {
                        v = 0;
                    }
                    if (collapsing_toolbar.getMeasuredHeight() - toolbar.getMeasuredHeight() <= Math.abs((float) verticalOffset)) {
                        if (nested.isNestedScrollingEnabled()) {
                            nested.setNestedScrollingEnabled(false);
                        }
                    } else {
                    }

//                    //动态改变标题栏透明度
//                    toolbar.getBackground().setAlpha((int) (255*v));
                }
            }
        });
        nested.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Log.e("AAAA", " " + nested.getScrollY());
                if (Math.abs(nested.getScrollY() - 0) >= 6) {
                    if (!nested.isNestedScrollingEnabled()) {
                        nested.setNestedScrollingEnabled(true);
                    }
                }
            }
        });
    }
}
