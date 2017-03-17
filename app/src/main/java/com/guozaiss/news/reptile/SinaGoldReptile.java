package com.guozaiss.news.reptile;

import com.guozaiss.news.beans.SinaGoldNew;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

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
    public static void getSinaGoldNews() {
        String url = "http://roll.finance.sina.com.cn/finance/gjs/hjfx/index.shtml";
        pickData(url);
    }

    /**
     * 爬取网页信息
     */
    private static void pickData(String url) {
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
                List<SinaGoldNew> sinaGoldNews = analyzeHTMLByString(response.body().string());
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
        Observable.from(list_009)
                .flatMap(new Func1<Element, Observable<Element>>() {
                    @Override
                    public Observable<Element> call(Element element) {
                        return Observable.from(element.children());
                    }
                })
                .map(new Func1<Element, SinaGoldNew>() {
                    @Override
                    public SinaGoldNew call(Element element) {
                        Element a = element.select("a").first();
                        String link = a.attr("abs:href");
                        String title = a.html();
                        String time = element.select("span").html();
                        try {
                            title = new String(title.getBytes("ISO8859-1"), "utf-8");
                            time = new String(time.getBytes("ISO8859-1"), "utf-8");
                            time = time.substring(time.indexOf("(") + 1, time.indexOf(")"));
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        return new SinaGoldNew(0, time, new Date(), title, link);
                    }
                })
                .subscribe(new Action1<SinaGoldNew>() {
                    @Override
                    public void call(SinaGoldNew sinaGoldNew) {
                        sinaGoldNews.add(sinaGoldNew);
                    }
                });
        return sinaGoldNews;
    }

}