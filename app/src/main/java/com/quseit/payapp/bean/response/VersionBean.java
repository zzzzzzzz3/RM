package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: VersionBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/31 17:11
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class VersionBean {
    @SerializedName(value = "version_name")
    private String versionName;
    @SerializedName(value = "version_code")
    private String versionCode;
    @SerializedName(value = "download_link")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }
}
