package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: QRResponseBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 09:49
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class QRResponseBean {

    private String code;
    private String message;
    @SerializedName(value = "qr")
    private String qrCodeUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }
}
