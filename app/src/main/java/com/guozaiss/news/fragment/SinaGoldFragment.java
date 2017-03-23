package com.guozaiss.news.fragment;

import com.guozaiss.news.R;
import com.guozaiss.news.core.base.view.BaseFragment;
import com.guozaiss.news.reptile.SinaGoldNew;
import com.guozaiss.news.reptile.SinaGoldReptile;

import java.util.List;

/**
 * 新浪黄金
 */
public class SinaGoldFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sina_gold;
    }

    @Override
    protected void initView() {
        SinaGoldReptile.getSinaGoldNews(new SinaGoldReptile.CallBack() {
            @Override
            public void pickData(List<SinaGoldNew> sinaGoldNews) {

            }
        });
    }

    @Override
    protected void initData() {

    }
}