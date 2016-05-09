package com.guozaiss.news.common.base;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.guozaiss.news.R;
import com.guozaiss.news.common.utils.ActivityManager;

/**
 * ActivityManager
 *
 * Created by guozaiss on 16/2/15.
 */
public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
//    private Map<String, Integer> toolbarAlpha = new HashMap<>();

//    protected int activityCloseEnterAnimation;
//    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LogUtils.e("onCreate" + this.getClass().getSimpleName());
        ActivityManager.getInstance().pushActivity(this);//push Activity
//        LogUtils.e("回退栈数量" + ActivityManager.getInstance().size() + "");
//        ButterKnife.bind(this);//注解方式

//        initAnimation();
    }

//    protected void initAnimation() {
//        TypedArray activityStyle = getTheme().obtainStyledAttributes(new int[]{android.R.attr.windowAnimationStyle});
//        int windowAnimationStyleResId = activityStyle.getResourceId(0, 0);
//        activityStyle.recycle();
//        activityStyle = getTheme().obtainStyledAttributes(windowAnimationStyleResId, new int[]{android.R.attr.activityCloseEnterAnimation, android.R.attr.activityCloseExitAnimation});
//        activityCloseEnterAnimation = activityStyle.getResourceId(0, 0);
//        activityCloseExitAnimation = activityStyle.getResourceId(1, 0);
//        activityStyle.recycle();
//    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        initView();
    }

    /**
     * 初始化toolbar
     */
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Integer integer = toolbarAlpha.get(this.getClass().getSimpleName());
//        toolbar.getBackground().setAlpha(integer == null ? 255 : integer);//保存标题栏属性
    }

    @Override
    protected void onPause() {
        super.onPause();
//        toolbarAlpha.put(this.getClass().getSimpleName(), toolbar.getBackground().getAlpha());//恢复标题栏属性
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
//        LogUtils.e("onDestroy" + this.getClass().getSimpleName());
        ActivityManager.getInstance().popActivity();//弹出Activity
//        LogUtils.e("回退栈数量" + ActivityManager.getInstance().size() + "");
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN){
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v,ev)){
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    private boolean isShouldHideKeyboard(View v, MotionEvent event){
        if (v != null && (v instanceof EditText)){
            int[] l = {0,0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom){
                //点击EditText的事件，忽略它
                return false;
            }else {
                v.clearFocus();
                return true;
            }
        }
        //如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他焦点
        return false;
    }

    //获取InputMethodManager，隐藏软键盘
    private void hideKeyboard(IBinder token) {
        if (token != null){
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
