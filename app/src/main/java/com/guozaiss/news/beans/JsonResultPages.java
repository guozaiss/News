package com.guozaiss.news.beans;

import java.util.List;

/**
 * Created by bruce on 16/4/10.
 */
public class JsonResultPages<T, C> extends JsonResult<T> {

    private List<C> rows;

    private Integer count;
    private Integer pageNo;
    private Integer pageSize;

    public List<C> getRows() {
        return rows;
    }

    public void setRows(List<C> rows) {
        this.rows = rows;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
