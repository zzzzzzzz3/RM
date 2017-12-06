package com.quseit.payapp.bean.response;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 文 件 名: UserBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/6 17:07
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

@Entity
public class UserBean {
    private String avatarUrl;

    private String firstName;

    @Id
    private String key;

    private String lastName;

    private String role;

    @Generated(hash = 754763596)
    public UserBean(String avatarUrl, String firstName, String key, String lastName,
            String role) {
        this.avatarUrl = avatarUrl;
        this.firstName = firstName;
        this.key = key;
        this.lastName = lastName;
        this.role = role;
    }

    @Generated(hash = 1203313951)
    public UserBean() {
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
