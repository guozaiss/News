package com.guozaiss.news.common.utils.http;

import com.guozaiss.news.entities.Data;
import com.guozaiss.news.entities.HotWord;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by guozaiss on 16/3/29.
 */
public interface DataService {
    @GET("query")
    Call<Data> getData(@Query("key") String key,@Query("q")String q);

    @GET("words")
    Call<HotWord> getHotWord( @Query("key")String key);

    @GET("http://www.meiyeplus.com/index.php")
    Call<Data> getTemp(@Query("controller") String controller, @Query("action") String action);
}

//    @GET("query")
//    Observable<Data> getData(@Query("key") String key,@Query("q")String q);
//
//    @GET("http://www.meiyeplus.com/index.php")
//    Call<Data> getTemp(@Query("controller") String controller, @Query("action") String action);