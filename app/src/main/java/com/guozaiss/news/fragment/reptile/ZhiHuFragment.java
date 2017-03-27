package com.guozaiss.news.fragment.reptile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.guozaiss.news.R;
import com.guozaiss.news.adapters.ZhiHuAdapter;
import com.guozaiss.news.core.base.view.BaseFragment;
import com.guozaiss.news.reptile.BaseReptile;
import com.guozaiss.news.reptile.ZhiHuModel;
import com.guozaiss.news.reptile.ZhiHuReptile;
import com.guozaiss.news.utils.AnimUtils;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayout;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayoutDirection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 知乎新闻
 */
public class ZhiHuFragment extends BaseFragment {

    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.img_refresh)
    ImageView imgRefresh;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sina_gold;
    }

    @Override
    protected void init() {
        requestData();
        listView.setEmptyView(imgRefresh);
        AnimUtils.startAnimation(getActivity(), imgRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorMenu));
        swipeRefreshLayout.setDirection(SwipeRefreshLayoutDirection.TOP);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipeRefreshLayoutDirection direction) {
                if (direction == SwipeRefreshLayoutDirection.TOP) {
                    requestData();
                }
            }
        });
        requestData();
    }

    private void requestData() {
        new ZhiHuReptile().getData("https://www.zhihu.com/rss", new BaseReptile.CallBack<ZhiHuModel>() {
            @Override
            public void pickData(List<ZhiHuModel> t) {
                swipeRefreshLayout.setRefreshing(false);
                listView.setAdapter(new ZhiHuAdapter(getActivity(), t));
                imgRefresh.clearAnimation();
            }

            @Override
            public void onErr() {
                swipeRefreshLayout.setRefreshing(false);
                listView.clearAnimation();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}