package com.quseit.payapp.bean.request;

/**
 * 文 件 名: MemberRequestBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/17 15:20
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MemberRequestBean {

    private String email;
    private String name;
    private String countryCode;
    private String phoneNumber;

    public MemberRequestBean() {
    }

    public MemberRequestBean(String email, String name, String countryCode, String phoneNumber) {
        this.email = email;
        this.name = name;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
