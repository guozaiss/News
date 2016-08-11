package com.guozaiss.news.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import com.guozaiss.news.BuildConfig;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.adapters.ViewPagerAdapter;
import com.guozaiss.news.core.base.view.BaseActivity;
import com.guozaiss.news.core.utils.AdUtils;
import com.guozaiss.news.core.utils.SPUtils;
import com.guozaiss.news.core.utils.ToastUtil;
import com.guozaiss.news.view.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private TabLayout tab_layout;
    private ViewPager view_pager;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (SPUtils.getBoolean(this, "night", false)) {
            setTheme(R.style.AppTheme_night);
        } else {
            setTheme(R.style.AppTheme);
        }
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

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
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        imageView = (ImageView) findViewById(R.id.imageView);
        List<NewsFragment> newsFragments = new ArrayList<>();
        for (int i = 0; i < Constants.type.length; i++) {
            NewsFragment newsFragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("type", Constants.type[i]);
            newsFragment.setArguments(bundle);
            newsFragments.add(newsFragment);
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), this, newsFragments);
        view_pager.setAdapter(adapter);
        tab_layout.setupWithViewPager(view_pager);
        if (BuildConfig.DEBUG) {
            ToastUtil.showToastOfLong("当前处于DEBUG模式，请谨慎操作！");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
//            boolean night = SPUtils.getBoolean(this, "night", false);
//            SPUtils.putBoolean(this, "night", !night);
//            Bitmap shot = CommonUtils.getShot(this);
//            imageView.setImageBitmap(shot);
//            imageView.setVisibility(View.VISIBLE);
//            startAnim(imageView);
//            recreate();
            AdUtils.showInterstitial(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startAnim(View view) {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(4000);//设置动画持续时间
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
        view.setAnimation(animation);
        animation.start();
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
        if (isExit == false) {
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
