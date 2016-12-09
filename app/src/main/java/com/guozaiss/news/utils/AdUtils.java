package com.guozaiss.news.utils;

import android.app.Activity;
import android.util.Log;
import android.widget.RelativeLayout;

import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;

/**
 * Created by Lenovo on 2016/6/8.
 */
public class AdUtils {

    public static void showBanner(Activity activity,RelativeLayout relativeLayout) {
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
        relativeLayout.addView(bv);
    }

//    public static void showBanner(final Activity activity) {
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                AdManager.getInstance().showRelationBanner(BannerSizeType.BANNER, BannerPositions.BOTTOM_CENTER, 0, activity);
//            }
//        }, 1000);
//    }
//
//    public static void showInterstitial(final Activity activity) {
//        if (AdManager.getInstance().isInterstitialReady()) {
//            AdManager.getInstance().showInterstitial(activity);
//        } else {
//            AdManager.getInstance().loadInterstitial(activity);
//        }
//    }
//
//    public static void  hide() {
//        AdManager.getInstance().removeBanner();
//    }

}
