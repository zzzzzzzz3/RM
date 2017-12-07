package com.quseit.payapp.bean.request;

/**
 * 文 件 名: Member
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 10:35
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class Member {

    private String type;
    private String countryCode;
    private String phoneNumber;

    public Member(String type, String countryCode, String phoneNumber) {
        this.type = type;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
    }
}
