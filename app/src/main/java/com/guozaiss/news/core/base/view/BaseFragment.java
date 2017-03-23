package com.guozaiss.news.core.base.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by guozaiss on 16/6/16.
 */
public abstract class BaseFragment extends Fragment {
    private View inflate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (this.inflate != null) {
            container.removeView(inflate);
            return inflate;
        } else {
            inflate = inflater.inflate(getLayoutId(), container, false);
            initView();
            initData();
            return inflate;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

}