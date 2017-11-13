package com.quseit.payapp.bean;

/**
 * 文 件 名: TransationBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 14:35
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationBean {

    private String orderNo;
    private String remark;
    private int iconRes;
    private String time;

    public TransationBean() {
    }

    public TransationBean(String orderNo, String remark, int iconRes, String time) {
        this.orderNo = orderNo;
        this.remark = remark;
        this.iconRes = iconRes;
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

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
