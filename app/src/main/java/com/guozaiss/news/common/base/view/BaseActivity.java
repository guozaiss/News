package com.guozaiss.news.common.base.view;

import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.guozaiss.news.R;
import com.guozaiss.news.common.utils.ActivityManagerE;
import com.guozaiss.news.common.utils.AdUtils;
import com.guozaiss.news.common.utils.LogUtils;

/**
 * 1、ActivityManagerE Activity管理栈
 * 2、初始化通用控件
 * 3、输入法统一管理
 * 4、OptionMenu逻辑抽取
 * 5、权限请求统一管理
 * <p/>
 * Created by guozaiss on 16/2/15.
 */
public class BaseActivity extends AppCompatActivity {
    protected Toolbar toolbar;
    protected SwipeRefreshLayout swipeRefreshLayout;
    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;//权限请求码


    //    private Map<String, Integer> toolbarAlpha = new HashMap<>();

//    protected int activityCloseEnterAnimation;
//    protected int activityCloseExitAnimation;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        LogUtils.e("onCreate" + this.getClass().getSimpleName());
        ActivityManagerE.getInstance().pushActivity(this);//push Activity
//        LogUtils.e("回退栈数量" + ActivityManagerE.getApplicationInstance().size() + "");
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
        try {
            toolbar = (Toolbar) findViewById(R.id.appbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
            swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#ff0000"), Color.parseColor("#00ff00"), Color.parseColor("#0000ff"));
        } catch (Exception e) {
            LogUtils.e("--无法找到资源ID----无法找到资源ID----无法找到资源ID----无法找到资源ID----无法找到资源ID--");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Integer integer = toolbarAlpha.get(this.getClass().getSimpleName());
//        toolbar.getBackground().setAlpha(integer == null ? 255 : integer);//保存标题栏属性

        AdUtils.showBanner(this);
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
    protected void onStop() {
        super.onStop();
        AdUtils.hide();
    }

    @Override
    protected void onDestroy() {
//        LogUtils.e("onDestroy" + this.getClass().getSimpleName());
        ActivityManagerE.getInstance().remove(this);//弹出Activity
        LogUtils.e("回退栈数量" + ActivityManagerE.size() + "");
        super.onDestroy();
    }

    @Override
    public void finish() {
        super.finish();
//        overridePendingTransition(activityCloseEnterAnimation, activityCloseExitAnimation);
    }

    /**
     * 请求权限  Manifest.permission.XXX
     * group:android.permission-group.CONTACTS 联系人权限
     *      permission:android.permission.WRITE_CONTACTS
     *      permission:android.permission.GET_ACCOUNTS
     *      permission:android.permission.READ_CONTACTS
     *
     * group:android.permission-group.PHONE 电话权限
     *      permission:android.permission.READ_CALL_LOG
     *      permission:android.permission.READ_PHONE_STATE
     *      permission:android.permission.CALL_PHONE
     *      permission:android.permission.WRITE_CALL_LOG
     *      permission:android.permission.USE_SIP
     *      permission:android.permission.PROCESS_OUTGOING_CALLS
     *      permission:com.android.voicemail.permission.ADD_VOICEMAIL
     *
     *      group:android.permission-group.CALENDAR 日历权限
     *      permission:android.permission.READ_CALENDAR
     *      permission:android.permission.WRITE_CALENDAR
     *
     * group:android.permission-group.CAMERA 照相机权限
     *      permission:android.permission.CAMERA
     *
     * group:android.permission-group.SENSORS 传感器权限
     *      permission:android.permission.BODY_SENSORS
     *
     * group:android.permission-group.LOCATION 定位权限
     *      permission:android.permission.ACCESS_FINE_LOCATION
     *      permission:android.permission.ACCESS_COARSE_LOCATION
     *
     * group:android.permission-group.STORAGE 存储权限
     *      permission:android.permission.READ_EXTERNAL_STORAGE
     *      permission:android.permission.WRITE_EXTERNAL_STORAGE
     *
     * group:android.permission-group.MICROPHONE 麦克风权限
     *      permission:android.permission.RECORD_AUDIO
     *
     * group:android.permission-group.SMS  短信权限
     *      permission:android.permission.READ_SMS
     *      permission:android.permission.RECEIVE_WAP_PUSH
     *      permission:android.permission.RECEIVE_MMS
     *      permission:android.permission.RECEIVE_SMS
     *      permission:android.permission.SEND_SMS
     *      permission:android.permission.READ_CELL_BROADCASTS
     *
     * @param permission 权限名称
     */
    protected void requestPermission(final String permission) {
        int hasWriteContactsPermission = ActivityCompat.checkSelfPermission(this, permission);
        if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
//            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
//                showMessageOKCancel("你需要请求以下权限\n" + permission.substring(permission.lastIndexOf(".") + 1),
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ActivityCompat.requestPermissions(BaseActivity.this, new String[]{permission},
//                                        REQUEST_CODE_ASK_PERMISSIONS);
//                            }
//                        });
//                return;
//            }
            ActivityCompat.requestPermissions(this, new String[]{permission}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        } else {
            execute(permission);
        }
    }

    /**
     * 请求权限后执行的操作
     *
     * @param permission
     */
    protected void execute(String permission) {

    }

    /**
     * 显示请求权限对话框
     * @param message
     * @param okListener
     */
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    /**
     * 请求权限回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    execute(permissions[0]);
                } else {
                    // Permission Denied
                    Toast.makeText(this, "权限：" + permissions[0].substring(permissions[0].lastIndexOf(".") + 1) + " 被拒绝！\n操作已限制。", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判断是否隐藏软键盘
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                //点击EditText的事件，忽略它
                return false;
            } else {
                v.clearFocus();
                return true;
            }
        }
        //如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他焦点
        return false;
    }

    //获取InputMethodManager，隐藏软键盘
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}