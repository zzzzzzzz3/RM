package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

/**
 * 文 件 名: ResponseBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 11:52
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class PayResponseBean {

    @SerializedName(value = "code")
    private String code;
    @SerializedName(value = "message")
    private String msg;
    private String paymentMethod;

    public String getData() {
        return paymentMethod;
    }

    public void setData(String data) {
        this.paymentMethod = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean success(){
        return code.equals("10000")||code.equals("20000");
    }

    @Override
    public String toString() {
        return "code:"+code+" msg:"+msg;
    }
}
