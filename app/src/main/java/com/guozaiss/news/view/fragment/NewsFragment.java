package com.guozaiss.news.view.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.adapters.NewTopAdapter;
import com.guozaiss.news.core.utils.LogUtils;
import com.guozaiss.news.core.utils.http.DataUtils;
import com.guozaiss.news.entities.News;
import com.guozaiss.news.view.activity.HtmlActivity;

import java.util.List;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment implements Callback<News> {
    private View inflate;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ListView listView;
    private NewTopAdapter adapter;
    private String type;
    private View load_empty;
    private View loading;

    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // create ContextThemeWrapper from the original Activity Context with the custom theme r);
        if (this.inflate != null) {
            container.removeView(inflate);
            return inflate;
        } else {
            inflate = inflater.inflate(R.layout.fragment_news, container, false);
            initView();
            initData();
            return inflate;
        }
    }

    private void initData() {
        type = getArguments().getString("type");
        DataUtils.getDataService().getNews(type, Constants.topAppKey).enqueue(this);
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) this.inflate.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorMenu));
        listView = (ListView) this.inflate.findViewById(R.id.listView);
        load_empty = this.inflate.findViewById(R.id.load_empty);
        loading = this.inflate.findViewById(R.id.loading);
        listView.setEmptyView(loading);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                DataUtils.getDataService().getNews(type, Constants.topAppKey).enqueue(NewsFragment.this);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News.ResultBean.DataBean item = ((NewTopAdapter) parent.getAdapter()).getItem(position);
                Intent intent = new Intent(getActivity(), HtmlActivity.class);
                intent.putExtra("url", item.getUrl());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResponse(Response<News> response, Retrofit retrofit) {
        loaded();
        if (response.body().getResult() != null) {
            if (adapter == null) {
                adapter = new NewTopAdapter(getActivity(), response.body().getResult().getData(), R.layout.item_news);
                listView.setAdapter(adapter);
            } else {
                adapter.changeLists(response.body().getResult().getData());
                adapter.notifyDataSetChanged();
            }
        } else {
            List<News.ResultBean.DataBean> lists = adapter.getLists();
            if (lists != null) {
                lists.clear();
            }
            adapter.notifyDataSetChanged();
        }
    }

    /**
     * 加载结束操作
     */
    private void loaded() {
        listView.setEmptyView(load_empty);
        loading.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailure(Throwable t) {
        loaded();
        LogUtils.e(t.getMessage() + "");
    }

}
