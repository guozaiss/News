package com.guozaiss.news.reptile;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Admin on 2017/3/24.
 */
public abstract class BaseReptile<T> {

    /**
     * 获取万年历信息
     *
     * @return List<SinaGoldModel>
     */
    public void getData(String url,CallBack callBack) {
        pickData(url, callBack);
    }

    /**
     * 爬取网页信息
     */
    private void pickData(String url, final CallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onErr();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                analyzeHTMLByString(response.body().string(),callBack);
            }
        });
    }


    protected abstract void analyzeHTMLByString(String html, CallBack callBack);


    public interface CallBack<T> {
        void pickData(List<T> t);

        void onErr();
    }
}