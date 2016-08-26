package com.guozaiss.news.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.guozaiss.news.APIService.NetworkSubscriber;
import com.guozaiss.news.APIService.impl.DataServiceImpl;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.activity.HtmlActivity;
import com.guozaiss.news.adapters.NewTopAdapter;
import com.guozaiss.news.beans.News;
import com.guozaiss.news.core.base.view.BaseFragment;
import com.guozaiss.news.utils.LogUtils;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends BaseFragment {
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
        DataServiceImpl.getNews(type, Constants.topAppKey, newsSubscriber);
    }

    NetworkSubscriber newsSubscriber = new NetworkSubscriber<News>() {
        @Override
        public void onSuccess(News data) {
            super.onSuccess(data);
            loaded();
            if (data != null && data.getResult().getData().size() > 0) {
                if (adapter == null) {
                    adapter = new NewTopAdapter(getActivity(), data.getResult().getData());
                    listView.setAdapter(adapter);
                } else {
                    adapter.setData(data.getResult().getData());
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

        @Override
        public void onError(Throwable e) {
            super.onError(e);
            loaded();
            LogUtils.e(e.getMessage() + "");
        }
    };

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
                DataServiceImpl.getNews(type, Constants.topAppKey, newsSubscriber);
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

    /**
     * 加载结束操作
     */
    private void loaded() {
        listView.setEmptyView(load_empty);
        loading.setVisibility(View.INVISIBLE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onNoFastClick(View view) {

    }
}
