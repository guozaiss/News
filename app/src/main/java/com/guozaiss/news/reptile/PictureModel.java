package com.guozaiss.news.reptile;

/**
 * Created by Admin on 2017/3/24.
 */
public class PictureModel {
    private float width;
    private float height;
    private String title;
    private String url;
    private long time;
    private String detailUrl;

    public PictureModel(float width, float height, String title, String url, long time, String detailUrl) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.url = url;
        this.time = time;
        this.detailUrl = detailUrl;
    }

    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
