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

    @SerializedName(value = "Amount")
    String amount;
    @SerializedName(value = "AuthCode")
    String authCode;
    @SerializedName(value = "Remark")
    String remark;
    @SerializedName(value = "StoreID")
    String storeId;

    public PayRequestBean() {
    }

    public PayRequestBean(String amount, String authCode, String remark, String storeId) {
        this.amount = amount;
        this.authCode = authCode;
        this.remark = remark;
        this.storeId = storeId;
    }
}