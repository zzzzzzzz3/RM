package com.quseit.payapp.bean.request;

/**
 * 文 件 名: TokenRequest
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 15:31
 * 修改时间：
 * 修改备注：
 */

public class TokenRequest {

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    private String refreshToken;

    public TokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
