package com.guozaiss.news.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reactivestreams.Publisher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Guo on 2017/3/1.
 */
public class SinaGoldReptile {

    /**
     * 单例工具类
     */
    private SinaGoldReptile() {
    }

    /**
     * 获取万年历信息
     *
     * @return List<SinaGoldNew>
     */
    public static void getSinaGoldNews(CallBack callBack) {
        String url = "http://roll.finance.sina.com.cn/finance/gjs/hjfx/index.shtml";
        pickData(url, callBack);
    }

    /**
     * 爬取网页信息
     */
    private static void pickData(String url, final CallBack callBack) {
        OkHttpClient client = new OkHttpClient();
        final Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                callBack.pickData(analyzeHTMLByString(response.body().string()));
            }
        });
    }

    /**
     * 使用jsoup解析网页信息
     */
    private static List<SinaGoldNew> analyzeHTMLByString(String html) {
        final List<SinaGoldNew> sinaGoldNews = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements list_009 = document.getElementsByClass("list_009");
        Flowable.fromIterable(list_009)
                .flatMap(new Function<Element, Publisher<Element>>() {
                    @Override
                    public Publisher<Element> apply(Element element) throws Exception {
                        return Flowable.fromIterable(element.children());
                    }
                })
                .map(new Function<Element, SinaGoldNew>() {
                    @Override
                    public SinaGoldNew apply(Element element) throws Exception {
                        Element a = element.select("a").first();
                        String link = a.attr("abs:href");
                        String title = a.html();
                        String time = element.select("span").html();
                        try {
                            title = new String(title.getBytes("ISO8859-1"), "utf-8");
                            time = new String(time.getBytes());
                            time = time.substring(time.indexOf("(") + 1, time.indexOf(")"));
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        return new SinaGoldNew(0, time, new Date(), title, link);
                    }
                })
                .subscribe(new Consumer<SinaGoldNew>() {
                    @Override
                    public void accept(SinaGoldNew sinaGoldNew) throws Exception {
                        sinaGoldNews.add(sinaGoldNew);
                    }
                });
        return sinaGoldNews;
    }

    public interface CallBack {
        void pickData(List<SinaGoldNew> sinaGoldNews);
    }
}