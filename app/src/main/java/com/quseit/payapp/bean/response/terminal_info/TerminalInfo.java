package com.quseit.payapp.bean.response.terminal_info;

/**
 * 文 件 名: TerminalInfo
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/5/15 10:27
 * 修改时间：
 * 修改备注：
 */

public class TerminalInfo {

    private Item item;

    private String code;

    public void setItem(Item item){
        this.item = item;
    }
    public Item getItem(){
        return this.item;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
}
