package com.quseit.payapp.bean.response.pay_v3;


import android.annotation.SuppressLint;

import com.quseit.pay.PayInfoBeanV3;
import com.quseit.payapp.util.TimeConverterUtil;

/**
 * 文 件 名: PayResponseV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 17:28
 * 修改时间：
 * 修改备注：
 */

public class PayResponseV3 {
    private Transaction item;
    private String code;
    public void setItem(Transaction item) {
        this.item = item;
    }
    public Transaction getData() {
        return item;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }
}
