package com.guozaiss.news.APIService;

import com.guozaiss.news.Constants;
import com.guozaiss.news.beans.Data;
import com.guozaiss.news.beans.HotWord;
import com.guozaiss.news.beans.News;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by guozaiss on 16/3/29.
 */
public interface DataService {
    @GET(Constants.news)
    Observable<Data> getData(@Query("key") String key, @Query("q")String q);

    @GET(Constants.words)
    Observable<HotWord> getHotWord(@Query("key")String key);

    /**
     * 新闻头条
     * @param type 类型
     * @param key key
     * @return
     */
    @GET(Constants.top)
    Observable<News> getNews(@Query("type")String type, @Query("key")String key);
}