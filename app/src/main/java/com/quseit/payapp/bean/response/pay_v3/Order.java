package com.quseit.payapp.bean.response.pay_v3;

import java.io.Serializable;

public class Order implements Serializable{

        private String id;
        private String title;
        private String detail;
        private String additionalData;
        private String currencyType;
        private int amount;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setTitle(String title) {
            this.title = title;
        }
        public String getTitle() {
            return title;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }
        public String getDetail() {
            return detail;
        }

        public void setAdditionalData(String additionalData) {
            this.additionalData = additionalData;
        }
        public String getAdditionalData() {
            return additionalData;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }
        public String getCurrencyType() {
            return currencyType;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
        public int getAmount() {
            return amount;
        }

    }