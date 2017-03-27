package com.guozaiss.news.reptile;

import android.text.Html;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
 * Created by Admin on 2017/3/27.
 */
public class ZhiHuReptile extends BaseReptile<ZhiHuModel> {

    @Override
    protected void analyzeHTMLByString(String html, final CallBack callBack) {
        final List<ZhiHuModel> zhiHuModels = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements item = document.getElementsByTag("item");
        Flowable.fromIterable(item)
                .subscribeOn(Schedulers.io())
                .map(new Function<Element, ZhiHuModel>() {
                    @Override
                    public ZhiHuModel apply(Element element) throws Exception {
                        Document parse = Jsoup.parse(element.toString().replace("\n","").replace("",""));
                        String title = parse.select("title").html();
                        String link = parse.select("guid").html();
                        String description = Html.fromHtml(parse.select("description").html().toString()).toString();
                        description = description.replaceAll("</?[^>]+>", "");
                        String pubdate = parse.select("pubdate").html();
                        String creator = parse.select("description").next().html();
                        return new ZhiHuModel(title, link, description, pubdate, creator, new Date().getTime());
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        if (zhiHuModels.size() > 0) {
                            callBack.pickData(zhiHuModels);
                        }
                    }
                })
                .subscribe(new Consumer<ZhiHuModel>() {
                    @Override
                    public void accept(ZhiHuModel zhiHuModel) throws Exception {
                        zhiHuModels.add(zhiHuModel);
                    }
                });
    }
}