package com.quseit.payapp.bean.request;

/**
 * 文 件 名: RefundRequestV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/5/15 11:19
 * 修改时间：
 * 修改备注：
 */

public class RefundRequestV3 {
    private String email;
    private String transactionId;
    private String pin;
    private String reason;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public RefundV3 getRefund() {
        return refund;
    }

    public void setRefund(RefundV3 refund) {
        this.refund = refund;
    }

    private RefundV3 refund;



    public RefundRequestV3(String reason,String pin,String email, String transactionId, RefundV3 refund) {
        this.email = email;
        this.transactionId = transactionId;
        this.refund = refund;
        this.pin = pin;
        this.reason = reason;
    }
}
