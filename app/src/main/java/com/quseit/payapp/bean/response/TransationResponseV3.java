package com.quseit.payapp.bean.response;

import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.bean.response.pay_v3.Transaction;

import java.util.List;

/**
 * 文 件 名: TransationResponseV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 19:47
 * 修改时间：
 * 修改备注：
 */

public class TransationResponseV3 {
    private List<Transaction> items;
    private String code;
    private Meta meta;
    private String cursor;

    public String getCursor() {
        return cursor;
    }

    public void setCursor(String cursor) {
        this.cursor = cursor;
    }

    public List<Transaction> getItems() {
        return items;
    }

    public void setItems(List<Transaction> items) {
        this.items = items;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public class Meta{
        private String count;
        private int total;

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
    }
}
