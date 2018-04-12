package com.quseit.payapp.bean.request.pay_v3;

/**
 * 文 件 名: PayRequestV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 17:21
 * 修改时间：
 * 修改备注：
 */

public class PayRequestV3 {
    private String authCode;
    private Order order;

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }


}
