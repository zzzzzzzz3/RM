package com.quseit.payapp.bean.request;

/**
 * 文 件 名: Filter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 14:16
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class Filter {

    private String status;

    public Filter(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
