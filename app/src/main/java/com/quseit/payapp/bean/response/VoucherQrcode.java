package com.quseit.payapp.bean.response;

/**
 * 文 件 名: VoucherQrcode
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/29 16:14
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VoucherQrcode {

    private String code;
    private String qrUrl;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getQrUrl() {
        return qrUrl;
    }

    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl;
    }
}
