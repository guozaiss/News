package com.guozaiss.news.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdView;
import com.guozaiss.news.BuildConfig;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.adapters.NewsAdapter;
import com.guozaiss.news.common.utils.AdUtils;
import com.guozaiss.news.common.base.BaseActivity;
import com.guozaiss.news.common.utils.LogUtils;
import com.guozaiss.news.common.utils.SPUtils;
import com.guozaiss.news.common.utils.http.DataUtils;
import com.guozaiss.news.entities.Data;
import com.guozaiss.news.entities.HotWord;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends BaseActivity implements Callback<Data>, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    List<Data.Result> result;
    private NewsAdapter newsAdapter;
    private List<String> hotwords;
    private ListView listView;
    private boolean refresh = true;
    private boolean refreshing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        boolean isFirst = SPUtils.getBoolean(this, "isFirst", false);
        if (BuildConfig.debug && !isFirst) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("致亲爱的用户：");
            builder.setMessage("        您好，本app集成了广告单元，如若影响您的使用,请手动关闭。");
            builder.setNegativeButton("关闭", null);
            builder.create().show();
            SPUtils.putBoolean(this, "isFirst", true);
        }
        swipeRefreshLayout.setOnRefreshListener(this);
        newsAdapter = new NewsAdapter(this, result, R.layout.item_news);
        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount - 1) {
                    getData(false);
                }
            }
        });
        listView.setAdapter(newsAdapter);
        View empty = findViewById(R.id.load_empty);
        View loading = findViewById(R.id.loading);
        listView.setEmptyView(loading);
        DataUtils.getDataService().getHotWord(Constants.AppKey).enqueue(new Callback<HotWord>() {

            @Override
            public void onResponse(Response<HotWord> response, Retrofit retrofit) {
                hotwords = response.body().getResult();
                DataUtils.getDataService().getData(Constants.AppKey, hotwords.get(0)).enqueue(MainActivity.this);
                hotwords.remove(0);
            }

            @Override
            public void onFailure(Throwable t) {
                LogUtils.e("获取数据失败");
            }
        });
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdUtils.init(mAdView, getApplicationContext());
    }

    @Override
    public void onRefresh() {
        getData(true);
    }

    public void getData(boolean refresh) {
        if (null != hotwords && hotwords.size() > 0) {
            if (!refreshing) {
                this.refresh = refresh;
                DataUtils.getDataService().getData(Constants.AppKey, hotwords.get(0)).enqueue(this);
                hotwords.remove(0);
                refreshing = true;
                LogUtils.e("请求数据中。。。。。。。");
            }
        }
    }

    @Override
    public void onResponse(Response<Data> response, Retrofit retrofit) {
        LogUtils.e("成功：" + response.body().toString());
        swipeRefreshLayout.setRefreshing(false);
        result = response.body().getResult();
        if (result != null && result.size() > 0) {
            if (refresh) {
                newsAdapter.changeLists(result);
            } else {
                newsAdapter.addLists(result);
            }
        } else {
            onRefresh();
        }
        refreshing = false;
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFailure(Throwable t) {
        LogUtils.e(t.getMessage() + "");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Data.Result item = ((NewsAdapter) parent.getAdapter()).getItem(position);
        Intent intent = new Intent(this, HtmlActivity.class);
        intent.putExtra("url", item.getUrl());
        startActivity(intent);
    }

}
