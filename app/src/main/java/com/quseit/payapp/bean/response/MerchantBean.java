package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 文 件 名: MerchantBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/5 10:45
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MerchantBean {

    @SerializedName(value = "merchantLogo")
    private String avatar;
    @SerializedName(value = "merchantName")
    private String merchant;

    private List<UserBean> users;

    public List<UserBean> getUsers() {
        return users;
    }

    public void setUsers(List<UserBean> users) {
        this.users = users;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }
}
