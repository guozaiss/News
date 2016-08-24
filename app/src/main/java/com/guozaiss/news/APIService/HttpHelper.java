package com.guozaiss.news.APIService;


import com.guozaiss.news.APIService.impl.DataServiceImpl;
import com.guozaiss.news.Constants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;


/**
 * Created by bruce on 16/4/22.
 */
public class HttpHelper {

    public static final String END_POINT = Constants.topBase;

    public static void initialize() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request()
                        .newBuilder()
//                        .addHeader("deviceId", (String) SPUtils.get(AppConfigs.SP_KEY_DEVICE_ID, ""))
//                        .addHeader("token", (String) SPUtils.get(AppConfigs.SP_KEY_USER_TOKEN, ""))
                        .build();

                long t1 = System.nanoTime();
                Timber.d("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());

                Response response = chain.proceed(request);
                long t2 = System.nanoTime();
                Timber.d("Received response for %s in  %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers());

                final String responseString = new String(response.body().bytes());
                //打印返回JSON结果
                Timber.d("returnJson %s", responseString);

                return response.newBuilder()
                        .body(ResponseBody.create(response.body().contentType(), responseString))
                        .build();
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(END_POINT)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        DataServiceImpl.dataService = retrofit.create(DataService.class);
    }
}
