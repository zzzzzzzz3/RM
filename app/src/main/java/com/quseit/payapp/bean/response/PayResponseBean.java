package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: ResponseBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 11:52
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class PayResponseBean {

    @SerializedName(value = "code")
    private String code;
    @SerializedName(value = "message")
    private String msg;
    private String paymentMethod;
    private String storeName;
    private String transactionId;
    private String transactionAt;
    private String paymentAmount;
    private String remark;

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionAt() {
        return transactionAt;
    }

    public void setTransactionAt(String transactionAt) {
        this.transactionAt = transactionAt;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getData() {
        return paymentMethod;
    }

    public void setData(String data) {
        this.paymentMethod = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean success(){
        return code.equals("10000")||code.equals("20000");
    }

    @Override
    public String toString() {
        return "code:"+code+" msg:"+msg;
    }
}
