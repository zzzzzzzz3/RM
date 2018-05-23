package com.quseit.payapp.bean.response.refund_users;

import java.util.List;

/**
 * 文 件 名: RefundUsers
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/5/15 11:11
 * 修改时间：
 * 修改备注：
 */

public class RefundUsers {

    private String code;
    private List<UserBean> items;

    public List<UserBean> getItems() {
        return items;
    }

    public void setItems(List<UserBean> items) {
        this.items = items;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
