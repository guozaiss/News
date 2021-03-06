package com.guozaiss.news.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDelegate;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.guozaiss.news.BuildConfig;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.adapters.ViewPagerAdapter;
import com.guozaiss.news.core.base.view.BaseActivity;
import com.guozaiss.news.fragment.NewsFragment;
import com.guozaiss.news.fragment.reptile.SinaGoldFragment;
import com.guozaiss.news.fragment.reptile.ZhiHuFragment;
import com.guozaiss.news.utils.AdUtils;
import com.guozaiss.news.utils.SPUtils;
import com.guozaiss.news.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity {
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.banner)
    RelativeLayout banner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        tip();
        List<Fragment> newsFragments = new ArrayList<>();
        for (int i = 0; i < Constants.type.length; i++) {
            NewsFragment newsFragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", Constants.type[i]);
            newsFragment.setArguments(bundle);
            newsFragments.add(newsFragment);
        }
        newsFragments.add(new SinaGoldFragment());
        newsFragments.add(new ZhiHuFragment());

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), newsFragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        if (BuildConfig.DEBUG) {
            ToastUtil.showToast("当前处于DEBUG模式");
        }

        AdUtils.showInterstitialAD(this);
        AdUtils.showBanner(this, banner);
    }

    private void tip() {
        boolean isFirst = SPUtils.getBoolean(this, "isFirst", false);
        //当是release版本并且是第一次运行时弹出对话框
        if (!BuildConfig.debug && !isFirst) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.mipmap.ic_launcher);
            builder.setTitle("致亲爱的用户：");
            builder.setMessage("        您好，本app集成了广告单元，如若影响您的使用,请手动关闭。");
            builder.setNegativeButton("关闭", null);
            builder.create().show();
            SPUtils.putBoolean(this, "isFirst", true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//                AdUtils.showInterstitial(this);
                return true;
            case R.id.action_switch:
                boolean night = SPUtils.getBoolean(this, "night", true);
//                Timber.e( getDelegate().getDefaultNightMode()+"");
                if (!night) {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                } else {
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                SPUtils.putBoolean(this, "night", !night);
                recreate();
                return true;
//            case R.id.action_open_or_close:
//                boolean open = SPUtils.getBoolean(this, "open", false);
//                if (!open) {
//                    ToastUtil.showToast("已关闭，将在下次启动生效！");
//                } else {
//                    ToastUtil.showToast("已开启，将在下次启动生效!");
//                }
//                SPUtils.putBoolean(this, "open", !open);
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitBy2Click(); //调用双击退出函数
        }
        return false;
    }

    /**
     * 双击退出函数
     */
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (!isExit) {
            isExit = true; // 准备退出
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false; // 取消退出
                }
            }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务
        } else {
            finish();
            System.exit(0);
        }
    }
}