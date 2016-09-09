package com.guozaiss.news.utils;

import android.app.Activity;
import android.os.Handler;

import com.keymob.networks.AdManager;
import com.keymob.networks.core.BannerPositions;
import com.keymob.networks.core.BannerSizeType;

/**
 * Created by Lenovo on 2016/6/8.
 */
public class AdUtils {

    public static void showBanner(final Activity activity) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AdManager.getInstance().showRelationBanner(BannerSizeType.BANNER, BannerPositions.BOTTOM_CENTER, 0, activity);
            }
        }, 1000);
    }

    public static void showInterstitial(final Activity activity) {
        if (AdManager.getInstance().isInterstitialReady()) {
            AdManager.getInstance().showInterstitial(activity);
        } else {
            AdManager.getInstance().loadInterstitial(activity);
        }
    }

    public static void  hide() {
        AdManager.getInstance().removeBanner();
    }

}
