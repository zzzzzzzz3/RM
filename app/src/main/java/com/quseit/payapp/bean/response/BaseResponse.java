package com.quseit.payapp.bean.response;

/**
 * 文 件 名: BaseResponse
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/6 16:48
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class BaseResponse<T> {

    private T message;

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
