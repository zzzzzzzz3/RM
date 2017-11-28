package com.quseit.payapp.bean.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * 文 件 名: TransationBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 14:35
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationBean {

    private int amount;

    private String createdAt;

    private String currency;

    private String id;

    private String memberProfile;

    private List<String> orderDescription ;

    private String orderId;

    private String orderTitle;

    private String paymentMethod;

    private String platform;

    private String refId;

    private String status;

    private String storeKey;

    private String terminalKey;

    private String transactionId;

    private String userKey;

    public void setAmount(int amount){
        this.amount = amount;
    }
    public int getAmount(){
        return this.amount;
    }
    public void setCreatedAt(String createdAt){
        this.createdAt = createdAt;
    }
    public String getCreatedAt(){
        return this.createdAt;
    }
    public void setCurrency(String currency){
        this.currency = currency;
    }
    public String getCurrency(){
        return this.currency;
    }
    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setMemberProfile(String memberProfile){
        this.memberProfile = memberProfile;
    }
    public String getMemberProfile(){
        return this.memberProfile;
    }
    public void setString(List<String> orderDescription){
        this.orderDescription = orderDescription;
    }
    public List<String> getString(){
        return this.orderDescription;
    }
    public void setOrderId(String orderId){
        this.orderId = orderId;
    }
    public String getOrderId(){
        return this.orderId;
    }
    public void setOrderTitle(String orderTitle){
        this.orderTitle = orderTitle;
    }
    public String getOrderTitle(){
        return this.orderTitle;
    }
    public void setPaymentMethod(String paymentMethod){
        this.paymentMethod = paymentMethod;
    }
    public String getPaymentMethod(){
        return this.paymentMethod;
    }
    public void setPlatform(String platform){
        this.platform = platform;
    }
    public String getPlatform(){
        return this.platform;
    }
    public void setRefId(String refId){
        this.refId = refId;
    }
    public String getRefId(){
        return this.refId;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStoreKey(String storeKey){
        this.storeKey = storeKey;
    }
    public String getStoreKey(){
        return this.storeKey;
    }
    public void setTerminalKey(String terminalKey){
        this.terminalKey = terminalKey;
    }
    public String getTerminalKey(){
        return this.terminalKey;
    }
    public void setTransactionId(String transactionId){
        this.transactionId = transactionId;
    }
    public String getTransactionId(){
        return this.transactionId;
    }
    public void setUserKey(String userKey){
        this.userKey = userKey;
    }
    public String getUserKey(){
        return this.userKey;
    }
}
