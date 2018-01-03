package com.quseit.pay;

/**
 * Created by quseitu on 2017/12/29.
 */

public class PayInfoBean {
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public PayInfoBean(String date, String storeName, String message, String paymentMethod, String transactionId, String transactionAt, String paymentAmount, String remark) {
        this.date = date;
        this.storeName = storeName;
        this.message = message;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.transactionAt = transactionAt;
        this.paymentAmount = paymentAmount;
        this.remark = remark;
    }

    public PayInfoBean() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    private String message;
    private String paymentMethod;
    private String storeName;
    private String transactionId;
    private String transactionAt;
    private String paymentAmount;
    private String remark;





}
