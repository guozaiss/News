package com.guozaiss.news.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.guozaiss.news.R;
import com.guozaiss.news.core.base.view.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class DrawableFragment extends BaseFragment {
    public static final String PREF_FILE_NAME = "testPREF";
    public static final String KAY_USER_LEARN_DRAWER = "use_learn_drawer";

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private boolean mUseLearnedDrawer;
    private boolean mFromSaveInstance;

    private View conventView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUseLearnedDrawer = Boolean.valueOf(readFromPreferces(getContext(), KAY_USER_LEARN_DRAWER, "false"));
        if (savedInstanceState != null) {
            mFromSaveInstance = true;
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_drawable;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void setUp(int resId, DrawerLayout drawerLayout, final Toolbar toolbar) {
        conventView = getActivity().findViewById(resId);
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!mUseLearnedDrawer) {
                    mUseLearnedDrawer = true;
                    saveToPreferces(getActivity(), KAY_USER_LEARN_DRAWER, mUseLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

        };
        if (!mUseLearnedDrawer && !mFromSaveInstance) {
            mDrawerLayout.openDrawer(conventView);
        }

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();//显示按钮
            }
        });
    }

    public static void saveToPreferces(Context context, String name, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(name, value);
        edit.apply();
    }

    public static String readFromPreferces(Context context, String name, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        return sharedPreferences.getString(name, defaultValue);
    }

}
