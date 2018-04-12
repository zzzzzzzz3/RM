package com.quseit.payapp.bean.response;

/**
 * 文 件 名: TokenBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 15:25
 * 修改时间：
 * 修改备注：
 */

public class TokenBean {

    private String accessToken;
    private int expiresIn;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
