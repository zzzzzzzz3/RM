package com.quseit.payapp.bean.request;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: PayRequestBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 09:37
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PayRequestBean {

    @SerializedName(value = "amount")
    int amount;
    @SerializedName(value = "authCode")
    String authCode;
    @SerializedName(value = "remark")
    String remark;

    public PayRequestBean() {
    }

    public PayRequestBean(int amount, String authCode, String remark) {
        this.amount = amount;
        this.authCode = authCode;
        this.remark = remark;
    }
}
