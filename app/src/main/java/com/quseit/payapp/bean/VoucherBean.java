package com.quseit.payapp.bean;

/**
 * 文 件 名: VoucherBean
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 11:20
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VoucherBean {
    private String voucherName;
    private String voucherRemark;
    private String voucherType;
    private int voucherCount;

    public VoucherBean() {
    }

    public VoucherBean(String voucherName, String voucherRemark, String voucherType, int voucherCount) {
        this.voucherName = voucherName;
        this.voucherRemark = voucherRemark;
        this.voucherType = voucherType;
        this.voucherCount = voucherCount;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName;
    }

    public String getVoucherRemark() {
        return voucherRemark;
    }

    public void setVoucherRemark(String voucherRemark) {
        this.voucherRemark = voucherRemark;
    }

    public String getVoucherType() {
        return voucherType;
    }

    public void setVoucherType(String voucherType) {
        this.voucherType = voucherType;
    }

    public int getVoucherCount() {
        return voucherCount;
    }

    public void setVoucherCount(int voucherCount) {
        this.voucherCount = voucherCount;
    }
}
