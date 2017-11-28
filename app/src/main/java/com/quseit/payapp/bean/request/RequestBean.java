package com.quseit.payapp.bean.request;

/**
 * 文 件 名: RequestBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/16 10:34
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RequestBean {

    private String cursor;

    public RequestBean(String cursor) {
        this.cursor = cursor;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }
}
