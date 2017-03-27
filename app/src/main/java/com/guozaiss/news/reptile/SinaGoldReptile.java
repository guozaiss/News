package com.guozaiss.news.reptile;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.reactivestreams.Publisher;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Guo on 2017/3/1.
 */
public class SinaGoldReptile extends BaseReptile<SinaGoldNew> {
    protected String getUrl() {
        return "http://roll.finance.sina.com.cn/finance/gjs/hjfx/index.shtml";
    }

    @Override
    protected void analyzeHTMLByString(String html, final CallBack callBack) {
        final List<SinaGoldNew> sinaGoldNews = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements list_009 = document.getElementsByClass("list_009");
        Flowable.fromIterable(list_009)
                .subscribeOn(Schedulers.io())
                .flatMap(new Function<Element, Publisher<Element>>() {
                    @Override
                    public Publisher<Element> apply(Element element) throws Exception {
                        return Flowable.fromIterable(element.children());
                    }
                })
                .subscribeOn(Schedulers.io())
                .map(new Function<Element, SinaGoldNew>() {
                    @Override
                    public SinaGoldNew apply(Element element) throws Exception {
                        Element a = element.select("a").first();
                        String link = a.attr("abs:href");
                        String title = a.html();
                        String time = element.select("span").html();
                        try {
//                            title = new String(title.getBytes("utf-8"), "gb2312");
                            time = new String(time.getBytes("gb2312"), "gbk");
                            time = time.substring(time.indexOf("(") + 1, time.indexOf(")"));
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        return new SinaGoldNew(0, time, new Date(), title, link);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        callBack.pickData(sinaGoldNews);
                    }
                })
                .subscribe(new Consumer<SinaGoldNew>() {
                    @Override
                    public void accept(SinaGoldNew sinaGoldNew) throws Exception {
                        sinaGoldNews.add(sinaGoldNew);
                    }
                })
        ;
    }
}