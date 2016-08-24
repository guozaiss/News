package com.guozaiss.news.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guozaiss.news.Constants;
import com.guozaiss.news.fragment.NewsFragment;

import java.util.List;

/**
 * Created by Lenovo on 2016/7/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private List<NewsFragment> newsFragments;
    public ViewPagerAdapter(FragmentManager fm,Context context,List<NewsFragment> newsFragments) {
        super(fm);
        this.context = context;
        this.newsFragments = newsFragments;
    }

    @Override
    public Fragment getItem(int position) {
        return newsFragments.get(position);
    }

    @Override
    public int getCount() {
        if (newsFragments != null) {
            return newsFragments.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.nameOfType[position];
    }
}
