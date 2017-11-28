package com.quseit.payapp.bean.response;

import java.util.List;

/**
 * 文 件 名: VoucherResponse
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/17 16:40
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VoucherResponse {
    private List<VoucherBean> items;
    private int total;
    private int count;
    private String cursor;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<VoucherBean> getItems() {
        return items;
    }

    public void setItems(List<VoucherBean> items) {
        this.items = items;
    }
}
