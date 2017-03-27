package com.guozaiss.news.reptile;

/**
 * Created by Admin on 2017/3/27.
 */
public class ZhiHuModel {
    private String title;
    private String link;
    private String description;
    private String pubdate;
    private String creator;
    private long creatTime;

    public ZhiHuModel(String title, String link, String description, String pubdate, String creator, long creatTime) {
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubdate = pubdate;
        this.creator = creator;
        this.creatTime = creatTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }
}