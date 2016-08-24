package com.guozaiss.news.beans;

import java.util.List;

/**
 * Created by Lenovo on 2016/5/8.
 */
public class Data extends JsonResult<Data> {
    private String reason;
    private List<Result> result;
    private int error_code;

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return this.result;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return this.error_code;
    }

    public class Result {
        private String title;
        private String content;
        private String img_width;
        private String full_title;
        private String pdate;
        private String src;
        private String img_length;
        private String img;
        private String url;
        private String pdate_src;

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitle() {
            return this.title;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getContent() {
            return this.content;
        }

        public void setImg_width(String img_width) {
            this.img_width = img_width;
        }

        public String getImg_width() {
            return this.img_width;
        }

        public void setFull_title(String full_title) {
            this.full_title = full_title;
        }

        public String getFull_title() {
            return this.full_title;
        }

        public void setPdate(String pdate) {
            this.pdate = pdate;
        }

        public String getPdate() {
            return this.pdate;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getSrc() {
            return this.src;
        }

        public void setImg_length(String img_length) {
            this.img_length = img_length;
        }

        public String getImg_length() {
            return this.img_length;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getImg() {
            return this.img;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrl() {
            return this.url;
        }

        public void setPdate_src(String pdate_src) {
            this.pdate_src = pdate_src;
        }

        public String getPdate_src() {
            return this.pdate_src;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "title='" + title + '\'' +
                    ", content='" + content + '\'' +
                    ", img_width='" + img_width + '\'' +
                    ", full_title='" + full_title + '\'' +
                    ", pdate='" + pdate + '\'' +
                    ", src='" + src + '\'' +
                    ", img_length='" + img_length + '\'' +
                    ", img='" + img + '\'' +
                    ", url='" + url + '\'' +
                    ", pdate_src='" + pdate_src + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Data{" +
                "reason='" + reason + '\'' +
                ", result=" + result +
                ", error_code=" + error_code +
                '}';
    }
}
