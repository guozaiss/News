package com.guozaiss.news.utils;

import com.keymob.networks.core.IAdEventListener;
import com.keymob.networks.core.IInterstitialPlatform;
import com.keymob.networks.core.PlatformAdapter;
import com.keymob.sdk.core.AdTypes;

/**
 * Created by Lenovo on 2016/7/17.
 */
public class AdEventListener implements IAdEventListener {
    @Override
    public void onLoadedSuccess(int arg0, Object arg1,
                                PlatformAdapter arg2) {
        LogUtils.d(arg2 + " onLoadedSuccess for type " + arg0 + " withdata " + arg1);
        if (arg0 == AdTypes.INTERSTITIAL) {
            ((IInterstitialPlatform) arg2).showInterstitial();
        }
    }

    @Override
    public void onLoadedFail(int arg0, Object arg1, PlatformAdapter arg2) {
        LogUtils.d(arg2 + " onLoadedFail for type " + arg0 + " withdata " + arg1);
    }

    @Override
    public void onAdOpened(int arg0, Object arg1, PlatformAdapter arg2) {
        LogUtils.d(arg2 + " onAdOpened for type " + arg0 + " withdata " + arg1);
    }

    @Override
    public void onAdClosed(int arg0, Object arg1, PlatformAdapter arg2) {
        LogUtils.d(arg2 + " onAdClosed for type " + arg0 + " withdata " + arg1);
    }

    @Override
    public void onAdClicked(int arg0, Object arg1, PlatformAdapter arg2) {
        LogUtils.d(arg2 + " onAdClicked for type " + arg0 + " withdata " + arg1);
    }

    @Override
    public void onOtherEvent(String eventName, int adtype, Object data,
                             PlatformAdapter adapter) {
        LogUtils.d(adapter + " onOtherEvent for type" + adtype + " withEvent " + eventName);
    }
}
