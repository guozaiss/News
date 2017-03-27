package com.guozaiss.news.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.guozaiss.news.APIService.NetworkSubscriber;
import com.guozaiss.news.APIService.impl.DataServiceImpl;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.activity.HtmlActivity;
import com.guozaiss.news.adapters.NewTopAdapter;
import com.guozaiss.news.beans.News;
import com.guozaiss.news.core.base.view.BaseFragment;
import com.guozaiss.news.utils.AnimUtils;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayout;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayoutDirection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class NewsFragment extends BaseFragment {
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.img_refresh)
    ImageView imgRefresh;
    private NewTopAdapter adapter;
    private String type;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sina_gold;
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
                List<News.ResultBean.DataBean> lists = adapter.getData();
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
            Timber.e(e.getMessage() + "");
        }

        @Override
        public void onComplete() {
            super.onComplete();
            imgRefresh.clearAnimation();
        }
    };

    protected void init() {
        listView.setEmptyView(imgRefresh);
        AnimUtils.startAnimation(getActivity(), imgRefresh);
        type = getArguments().getString("type");
        DataServiceImpl.getNews(type, Constants.topAppKey, newsSubscriber);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorMenu));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipeRefreshLayoutDirection direction) {
                if (direction == SwipeRefreshLayoutDirection.TOP) {
                    DataServiceImpl.getNews(type, Constants.topAppKey, newsSubscriber);
                    swipeRefreshLayout.setRefreshing(true);
                }
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}