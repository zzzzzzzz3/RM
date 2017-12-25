package com.quseit.payapp.bean.response;

/**
 * 文 件 名: GoodBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 15:42
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class GoodBean {


    private String name;
    private int amount;

    public GoodBean() {
    }

    public GoodBean(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
