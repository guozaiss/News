package com.guozaiss.news.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.guozaiss.news.Constants;
import com.guozaiss.news.R;
import com.guozaiss.news.core.base.view.BaseActivity;
import com.qq.e.ads.splash.SplashAD;
import com.qq.e.ads.splash.SplashADListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class SplashActivity extends BaseActivity implements AMapLocationListener, SplashADListener {
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    @BindView(R.id.txt)
    TextView txt;
    @BindView(R.id.container)
    RelativeLayout container;
    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //声明mLocationOption对象
        AMapLocationClientOption mLocationOption = null;
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        new RxPermissions(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        if (aBoolean) {
                            fetchSplashAD(SplashActivity.this, container, txt, Constants.APPID, Constants.SplashPosID, SplashActivity.this, 2000);
                        } else {
                            next();
                        }
                    }
                });
    }

    @Override
    protected void execute(String permission) {
        switch (permission) {
            case Manifest.permission.ACCESS_FINE_LOCATION:
                //启动定位
                mLocationClient.startLocation();
                break;
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
//        if (aMapLocation != null) {
//            if (aMapLocation.getErrorCode() == 0) {
//                //定位成功回调信息，设置相关消息
//                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                aMapLocation.getLatitude();//获取纬度
//                aMapLocation.getLongitude();//获取经度
//                aMapLocation.getAccuracy();//获取精度信息
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date(aMapLocation.getTime());
//                df.format(date);//定位时间
//                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                String country = aMapLocation.getCountry();//国家信息
//                aMapLocation.getProvince();//省信息
//                aMapLocation.getCity();//城市信息
//                aMapLocation.getDistrict();//城区信息
//                aMapLocation.getStreet();//街道信息
//                aMapLocation.getStreetNum();//街道门牌号信息
//                aMapLocation.getCityCode();//城市编码
//                aMapLocation.getAdCode();//地区编码
//                aMapLocation.getAoiName();//获取当前定位点的AOI信息
//                Timber.e(aMapLocation.getCountry() + "");
//                mLocationClient.stopLocation();//停止定位
//                ToastUtil.showToast("定位成功！");
//                new Timer().schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
//                        finish();
//                    }
//                }, 1000);
//            } else {
//                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
////                LogUtils.e("location Error, ErrCode:"
////                        + aMapLocation.getErrorCode() + ", errInfo:"
////                        + aMapLocation.getErrorInfo());
////                startActivity(new Intent(SplashActivity.this, MainActivity.class));
////                finish();
//            }
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位
        mLocationClient.onDestroy();//销毁定位客户端。
    }

    /**
     * 拉取开屏广告，开屏广告的构造方法有3种，详细说明请参考开发者文档。
     *
     * @param activity      展示广告的activity
     * @param adContainer   展示广告的大容器
     * @param skipContainer 自定义的跳过按钮：传入该view给SDK后，SDK会自动给它绑定点击跳过事件。SkipView的样式可以由开发者自由定制，其尺寸限制请参考activity_splash.xml或下面的注意事项。
     * @param appId         应用ID
     * @param posId         广告位ID
     * @param adListener    广告状态监听器
     * @param fetchDelay    拉取广告的超时时长：取值范围[3000, 5000]，设为0表示使用广点通SDK默认的超时时长。
     */
    private void fetchSplashAD(Activity activity, ViewGroup adContainer, View skipContainer,
                               String appId, String posId, SplashADListener adListener, int fetchDelay) {
        new SplashAD(activity, adContainer, skipContainer, appId, posId, adListener, fetchDelay);
    }

    @Override
    public void onADPresent() {
        Log.i("Guo", "SplashADPresent");
        img.setVisibility(View.INVISIBLE); // 广告展示后一定要把预设的开屏图片隐藏起来
    }

    private void next() {
        Flowable
                .timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    }
                });
    }

    @Override
    public void onADDismissed() {
        next();
    }

    @Override
    public void onNoAD(int arg0) {
        Log.i("Guo", "LoadSplashADFail,ecode=" + arg0);
        next();
    }


    @Override
    public void onADClicked() {
        Log.i("Guo", "SplashADClicked");
    }

    /**
     * 倒计时回调，返回广告还将被展示的剩余时间。
     * 通过这个接口，开发者可以自行决定是否显示倒计时提示，或者还剩几秒的时候显示倒计时
     *
     * @param millisUntilFinished 剩余毫秒数
     */
    @Override
    public void onADTick(long millisUntilFinished) {
        Log.i("Guo", "SplashADTick " + millisUntilFinished + "ms");
        txt.setText(String.format("倒计时", Math.round(millisUntilFinished / 1000f)));
    }

    //防止用户返回键退出APP
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}