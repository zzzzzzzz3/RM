package com.quseit.payapp.bean.request;

/**
 * 文 件 名: TransationsRequest
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 12:09
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationsRequest {

    private String cursor;
    private String startAt;
    private String endAt;
    private Filter filter;

    public TransationsRequest(String cursor, String startAt, String endAt,Filter filter) {
        this.cursor = cursor;
        this.startAt = startAt;
        this.endAt = endAt;
        this.filter = filter;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }
}
