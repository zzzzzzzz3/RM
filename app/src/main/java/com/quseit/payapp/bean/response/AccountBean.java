package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: AccountBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/25 10:34
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class AccountBean {
    @SerializedName(value = "token")
    private String token;
    @SerializedName(value = "user_id")
    private String userId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
