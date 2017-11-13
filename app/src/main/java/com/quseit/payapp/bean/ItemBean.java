package com.quseit.payapp.bean;

import java.io.Serializable;

/**
 * 文 件 名: ItemBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 17:55
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class ItemBean implements Serializable{
    public int colorSrc;
    public String itemName;
    public int imgSrc;

    public ItemBean(int colorSrc, String itemName, int imgSrc) {
        this.colorSrc = colorSrc;
        this.itemName = itemName;
        this.imgSrc = imgSrc;
    }
}
