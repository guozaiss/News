package com.guozaiss.news.beans;

/**
 * Created by bruce on 16/4/7.
 */
public class JsonResult<T> {

    private Integer errorCode;
    private String errorMessage;

    private T data;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return (errorCode != null && errorCode == 200);
    }
}
