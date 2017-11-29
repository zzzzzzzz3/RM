package com.quseit.payapp.bean.response;

/**
 * 文 件 名: VoucherBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 11:20
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VoucherBean {
    private int amount;

    private int balanceQuantity;

    private String createdAt;

    private int discountRate;

    private Expiry expiry;

    private String id;

    private String imageUrl;

    private String label;

    private int minimumSpendAmount;

    private String origin;

    private int quantity;

    private String reason;

    private String status;

    private String type;

    private String uniqueCodes;

    private int usedQuantity;

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setBalanceQuantity(int balanceQuantity) {
        this.balanceQuantity = balanceQuantity;
    }

    public int getBalanceQuantity() {
        return this.balanceQuantity;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setDiscountRate(int discountRate) {
        this.discountRate = discountRate;
    }

    public int getDiscountRate() {
        return this.discountRate;
    }

    public void setExpiry(Expiry expiry) {
        this.expiry = expiry;
    }

    public Expiry getExpiry() {
        return this.expiry;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void setMinimumSpendAmount(int minimumSpendAmount) {
        this.minimumSpendAmount = minimumSpendAmount;
    }

    public int getMinimumSpendAmount() {
        return this.minimumSpendAmount;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setUniqueCodes(String uniqueCodes) {
        this.uniqueCodes = uniqueCodes;
    }

    public String getUniqueCodes() {
        return this.uniqueCodes;
    }

    public void setUsedQuantity(int usedQuantity) {
        this.usedQuantity = usedQuantity;
    }

    public int getUsedQuantity() {
        return this.usedQuantity;
    }
}
