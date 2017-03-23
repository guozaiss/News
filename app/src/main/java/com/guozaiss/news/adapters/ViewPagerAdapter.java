package com.guozaiss.news.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.guozaiss.news.Constants;

import java.util.List;

/**
 * Created by Lenovo on 2016/7/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> newsFragments;

    public ViewPagerAdapter(FragmentManager fm, List<Fragment> newsFragments) {
        super(fm);
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