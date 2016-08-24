package com.guozaiss.news.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.guozaiss.news.R;
import com.guozaiss.news.core.annotations.OrdersActionTag;
import com.guozaiss.news.core.annotations.OrdersModuleTag;
import com.guozaiss.news.core.base.view.BaseActivity;
import com.guozaiss.news.utils.SimpleBackPage;


/**
 * Created by bruce on 15/12/5.
 */
@OrdersModuleTag(moduleName = "simpleBackAction")
public class SimpleBackActivity extends BaseActivity {

    public final static String BUNDLE_KEY_PAGE = "BUNDLE_KEY_PAGE";
    public final static String BUNDLE_KEY_ARGS = "BUNDLE_KEY_ARGS";

    private String TAG = this.getClass().getSimpleName();
    private int mPageValue = -1;

    public Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mPageValue == -1) {
            mPageValue = getIntent().getIntExtra(BUNDLE_KEY_PAGE, 0);
        }

        //加载内容模块
        initFragment();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_simple_back;
    }

    @Override
    public void onNoFastClick(View view) {

    }

    protected void initFragment() {
        SimpleBackPage page = SimpleBackPage.getPageByValue(mPageValue);
        if (page == null) {
            throw new IllegalArgumentException("can not find page by value:" + mPageValue);
        }

        try {
            mFragment = (Fragment) page.getClz().newInstance();

            //传递参数
            Bundle args = getIntent().getBundleExtra(BUNDLE_KEY_ARGS);
            if (args != null) {
                mFragment.setArguments(args);
            }

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, mFragment, TAG);
            transaction.commitAllowingStateLoss();
        } catch (Exception e) {
            throw new IllegalArgumentException("generate fragment error by value" + mPageValue);
        }
    }

    @OrdersActionTag(actionName = "close")
    void doBackAction() {
        AppManager.getInstance().finishActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            doBackAction();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mFragment != null) {
            mFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop = {0, 0};
            //获取输入框当前的location位置
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击的是输入框区域，保留点击EditText的事件
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
}
