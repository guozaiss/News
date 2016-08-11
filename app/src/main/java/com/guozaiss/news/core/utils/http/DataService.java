package com.guozaiss.news.core.utils.http;

import com.guozaiss.news.Constants;
import com.guozaiss.news.entities.Data;
import com.guozaiss.news.entities.HotWord;
import com.guozaiss.news.entities.News;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by guozaiss on 16/3/29.
 */
public interface DataService {
    @GET(Constants.news)
    Call<Data> getData(@Query("key") String key,@Query("q")String q);

    @GET(Constants.words)
    Call<HotWord> getHotWord( @Query("key")String key);

    /**
     * 新闻头条
     * @param type 类型
     * @param key key
     * @return
     */
    @GET(Constants.top)
    Call<News> getNews(@Query("type")String type, @Query("key")String key);
}

//    @GET("query")
//    Observable<Data> getData(@Query("key") String key,@Query("q")String q);
//
//    @GET("http://www.meiyeplus.com/index.php")
//    Call<Data> getTemp(@Query("controller") String controller, @Query("action") String action);