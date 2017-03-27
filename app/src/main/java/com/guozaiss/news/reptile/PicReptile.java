package com.guozaiss.news.reptile;

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
 * Created by Admin on 2017/3/24.
 */
public class PicReptile extends BaseReptile<PictureModel> {
    protected String getUrl() {
        return "http://www.zbjuran.com/mei/xinggan/";
    }

    @Override
    protected void analyzeHTMLByString(String html, final CallBack callBack) {
        final List<PictureModel> pictureModels = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements elementsByClass = document.getElementsByClass("pic-list mb20 list");
        Element element = elementsByClass.get(0);
        Elements li = element.getElementsByTag("li");
        Flowable.fromIterable(li)
                .subscribeOn(Schedulers.io())
                .map(new Function<Element, PictureModel>() {
                    @Override
                    public PictureModel apply(Element element) throws Exception {
                        Element img = element.select("img").first();
                        String imgUrl = "http://www.zbjuran.com" + img.attr("data-original");
                        float width = Float.parseFloat(img.attr("width"));
                        float height = Float.parseFloat(img.attr("height"));
                        Element name = element.select("a").last();
                        String title = name.attr("title");
                        String href = "http://www.zbjuran.com" + name.attr("href");
                        return new PictureModel(width, height, title, imgUrl, new Date().getTime(), href);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        callBack.pickData(pictureModels);
                    }
                })
                .subscribe(new Consumer<PictureModel>() {
                    @Override
                    public void accept(PictureModel pictureModel) throws Exception {
                        pictureModels.add(pictureModel);
                    }
                });
    }
}