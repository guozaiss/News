package com.guozaiss.news.APIService.impl;

import com.guozaiss.news.APIService.DataService;
import com.guozaiss.news.beans.Data;
import com.guozaiss.news.beans.News;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * Created by guozaiss on 16/3/29.
 */
public class DataServiceImpl {
    public static DataService dataService;

    /** 获取数据
     *
     * @param key
     * @param q
     * @param subscriber
     */
    public static void getData(String key, String q, Subscriber<Data> subscriber) {
        dataService.getData(key, q)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

    /**
     * 获取新闻
     * @param type
     * @param key
     * @param subscriber
     */
    public static void getNews(String type, String key, Subscriber<News> subscriber) {
        dataService.getNews(type,key)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(subscriber);
    }

}