package com.quseit.payapp.bean.response.pay_v3;

import android.annotation.SuppressLint;

import com.quseit.pay.PayInfoBeanV3;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.TimeConverterUtil;

import java.io.Serializable;

public class Transaction implements Serializable{

        private Store store;
        private String transactionId;
        private String referenceId;
        private Order order;
        private String platform;
        private String method;
        private String type;
        private String status;
        private Error error;
        private String createdAt;
        private String  updatedAt;

    public String getReferenceId() {
            return referenceId;
        }

        public void setReferenceId(String referenceId) {
            this.referenceId = referenceId;
        }

        public void setStore(Store store) {
            this.store = store;
        }
        public Store getStore() {
            return store;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }
        public String getTransactionId() {
            return transactionId;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
        public Order getOrder() {
            return order;
        }

        public void setPlatform(String platform) {
            this.platform = platform;
        }
        public String getPlatform() {
            return platform;
        }

        public void setMethod(String method) {
            this.method = method;
        }
        public String getMethod() {
            return method;
        }

        public void setType(String type) {
            this.type = type;
        }
        public String getType() {
            return type;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
        }

        public void setError(Error error) {
            this.error = error;
        }
        public Error getError() {
            return error;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }
        public String getCreatedAt() {
            return createdAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }
        public String getUpdatedAt() {
            return updatedAt;
        }


    @SuppressLint("DefaultLocale")
    public PayInfoBeanV3 getPrintInfo(){
        PayInfoBeanV3 payInfoBean = new PayInfoBeanV3();
        String strDate = TimeConverterUtil.utc2Local(this.getCreatedAt(),"yyyy-MM-dd'T'HH:mm:ss'Z'","yy/MM/dd HH:mm:ss");
        String[] date = strDate.split(" ");
        payInfoBean.setDate(date[0]);
        payInfoBean.setTime(date[1]);
        payInfoBean.setStoreName(this.getStore().getName());
        payInfoBean.setMerchantName(PreferenceUtil.getInstance().getStr(GlobalBean.MERCHANT));
        payInfoBean.setMerchantId(PreferenceUtil.getInstance().getStr(GlobalBean.MERCHANT_ID));
        payInfoBean.setTransactionId(this.getTransactionId());
        payInfoBean.setMethod(this.getMethod());
        payInfoBean.setType(this.getType());
        payInfoBean.setReferenceId(this.getOrder().getId());
        payInfoBean.setTerminalId(PreferenceUtil.getInstance().getStr(GlobalBean.TERMINAL_ID));
        payInfoBean.setAmount(String.format("%.2f",this.getOrder().getAmount()/100.0));
        payInfoBean.setRemark(this.getOrder().getAdditionalData());
        payInfoBean.setApprcode(this.getReferenceId());
        return payInfoBean;
    }

    }