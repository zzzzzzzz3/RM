package com.quseit.payapp.bean.request;

/**
 * 文 件 名: RefundV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/5/15 11:14
 * 修改时间：
 * 修改备注：
 */

public class RefundV3 {
    private String type = "FULL";
    private String currencyType = "MYR";
    private int amount;

    public RefundV3(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
