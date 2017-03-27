package com.guozaiss.news.fragment.reptile;

import android.widget.ImageView;
import android.widget.ListView;

import com.guozaiss.news.R;
import com.guozaiss.news.adapters.PicAdapter;
import com.guozaiss.news.core.base.view.BaseFragment;
import com.guozaiss.news.reptile.BaseReptile;
import com.guozaiss.news.reptile.PictureModel;
import com.guozaiss.news.reptile.PictureReptile;
import com.guozaiss.news.utils.AnimUtils;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayout;
import com.guozaiss.news.view.swipeLayout.SwipeRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新浪黄金
 */
public class SinaGoldFragment extends BaseFragment {
    @BindView(R.id.img_refresh)
    ImageView imgRefresh;
    private String url = "http://www.zbjuran.com/mei/xinggan/";
    private int index = 1;
    @BindView(R.id.listView)
    ListView listView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    private List<PictureModel> pictureModels = new ArrayList<>();
    private PicAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sina_gold;
    }

    @Override
    protected void init() {

        listView.setEmptyView(imgRefresh);
        AnimUtils.startAnimation(getActivity(), imgRefresh);
        swipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent), getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorMenu));
        swipeRefreshLayout.setDirection(SwipeRefreshLayoutDirection.BOTH);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipeRefreshLayoutDirection direction) {
                if (direction == SwipeRefreshLayoutDirection.TOP) {
                    index = 1;
                } else if (direction == SwipeRefreshLayoutDirection.BOTTOM) {
                    index++;
                }
                requestData();
            }
        });
        requestData();
    }

    private void requestData() {
        new PictureReptile().getData(url.concat("list_13_").concat(index + "").concat(".html"), new BaseReptile.CallBack<PictureModel>() {

            @Override
            public void pickData(List<PictureModel> modelList) {
                imgRefresh.clearAnimation();
                swipeRefreshLayout.setRefreshing(false);
                if (index == 1) {
                    pictureModels.clear();
                }
                pictureModels.addAll(modelList);
                if (adapter == null) {
                    adapter = new PicAdapter(getActivity(), pictureModels);
                    listView.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onErr() {
                imgRefresh.clearAnimation();
            }
        });
    }
}