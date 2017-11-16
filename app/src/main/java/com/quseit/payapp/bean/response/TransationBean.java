package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: TransationBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 14:35
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationBean {

    @SerializedName(value = "id")
    private String orderNo;
    @SerializedName(value = "remark")
    private String remark;
    private String iconFont;
    @SerializedName(value = "createdAt")
    private String time;
    private String paymentMethod;
    private String status;
    private String payload;
    private String description;

    public String getIconFont() {
        return iconFont;
    }

    public void setIconFont(String iconFont) {
        this.iconFont = iconFont;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TransationBean() {
    }

    public TransationBean(String orderNo, String remark, String iconFont, String time) {
        this.orderNo = orderNo;
        this.remark = remark;
        this.iconFont = iconFont;
        this.time = time;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
