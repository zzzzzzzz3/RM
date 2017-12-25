package com.quseit.payapp.bean.request;

/**
 * 文 件 名: PointsRequestBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/17 15:40
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PointsRequestBean {

    private int amount;
    private String type;
    private String phoneNumber;
    private String countryCode;
    private String memberId;

    public PointsRequestBean() {
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public PointsRequestBean(int amount, String type, String phoneNumber, String countryCode , String memberId) {
        this.amount = amount;
        this.type = type;
        this.phoneNumber = phoneNumber;

        this.countryCode = countryCode;
        this.memberId=memberId;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
