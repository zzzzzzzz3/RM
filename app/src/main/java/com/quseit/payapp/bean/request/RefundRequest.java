package com.quseit.payapp.bean.request;

/**
 * 文 件 名: RefundRequest
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 14:42
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RefundRequest {

    private String key;
    private String reason;

    public RefundRequest(String key, String reason) {
        this.key = key;
        this.reason = reason;
    }
}
