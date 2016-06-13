package com.guozaiss.news.common.utils;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.guozaiss.news.BuildConfig;

/**
 * Created by Lenovo on 2016/6/8.
 */
public class AdUtils {
    public static void init(AdView mAdView, Context context) {
        if (!BuildConfig.DEBUG) {
            MobileAds.initialize(context, "ca-app-pub-3157595213810745~5268757115");
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
        }
    }
}
