package com.quseit.payapp.bean.request;

import java.io.Serializable;

/**
 * 文 件 名: Member
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 10:35
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class Member implements Serializable{

    public static final String ID = "ID";
    public static final String PHONENUMBER = "PHONENUMBER";

    private String type;
    private String countryCode;
    private String phoneNumber;
    private long memberId;

    public Member(String type, String countryCode, String phoneNumber,long memberId) {
        this.type = type;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.memberId = memberId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public long getMemberId() {
        return memberId;
    }

    public void setMemberId(long memberId) {
        this.memberId = memberId;
    }
}
