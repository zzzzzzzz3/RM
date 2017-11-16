package com.quseit.payapp.bean.response;

import java.util.List;

/**
 * 文 件 名: TransationResponse
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/16 10:33
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationResponse {

    private List<TransationBean> items;

    public List<TransationBean> getItems() {
        return items;
    }

    public void setItems(List<TransationBean> items) {
        this.items = items;
    }
}
