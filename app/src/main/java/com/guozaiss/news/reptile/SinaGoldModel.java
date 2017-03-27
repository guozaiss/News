package com.guozaiss.news.reptile;

import java.util.Date;

public class SinaGoldModel {
    private Integer newId;

    private String publishTime;

    private Date createTime;

    private String title;

    private String url;

    public SinaGoldModel(Integer newId, String publishTime, Date createTime, String title, String url) {
        this.newId = newId;
        this.publishTime = publishTime;
        this.createTime = createTime;
        this.title = title;
        this.url = url;
    }

    public SinaGoldModel() {
        super();
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime == null ? null : publishTime.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }
}