package com.guozaiss.news.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.ads.interstitial.AbstractInterstitialADListener;
import com.qq.e.ads.interstitial.InterstitialAD;

/**
 * Created by Lenovo on 2016/6/8.
 */
public class AdUtils {

    public static void showBanner(Activity activity,RelativeLayout relativeLayout) {
        try {
            // 创建Banner广告AdView对象
            BannerView bv = new BannerView(activity, ADSize.BANNER, "1105359035","6060119311509788");
            bv.setRefresh(30);
            bv.setADListener(new AbstractBannerADListener() {

                @Override
                public void onNoAD(int arg0) {
                    Log.i("AD_DEMO", "BannerNoAD，eCode=" + arg0);
                }

                @Override
                public void onADReceiv() {
                    Log.i("AD_DEMO", "ONBannerReceive");
                }
            });
//            bv.loadAD();
            relativeLayout.addView(bv);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public static void showInterstitialAD(Activity activity) {
        final InterstitialAD iad = new InterstitialAD(activity, "1105359035","3000014371206870");
        iad.setADListener(new AbstractInterstitialADListener() {
            @Override
            public void onADReceive() {
                iad.showAsPopupWindow();

            }

            @Override
            public void onNoAD(int arg0) {
                Log.i("AD_DEMO", "LoadInterstitialAd Fail:" + arg0);
            }

        });
        //请求插屏广告，每次重新请求都可以调用此方法。
//        iad.loadAD();
    }

}