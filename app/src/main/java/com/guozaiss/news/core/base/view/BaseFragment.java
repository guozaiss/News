package com.guozaiss.news.core.base.view;

import android.support.v4.app.Fragment;
import android.view.View;

import com.guozaiss.news.utils.EventUtils;

/**
 * Created by guozaiss on 16/6/16.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onClick(View view) {
        if (!EventUtils.isFastDoubleClick(view.getId())) {
            onNoFastClick(view);
        }
    }

    public abstract void onNoFastClick(View view);
}
