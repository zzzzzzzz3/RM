package com.quseit.payapp.bean.request.pay_v3;

public class Order {

        private int amount;
        private String currencyType;
        private String id;
        private String title;
        private String detail;
        private String additionalData;

        public Order(int amount, String currencyType, String id, String title, String detail, String additionalData) {
            this.amount = amount;
            this.currencyType = currencyType;
            this.id = id;
            this.title = title;
            this.detail = detail;
            this.additionalData = additionalData;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getAmount() {
            return amount;
        }

        public void setCurrencyType(String currencyType) {
            this.currencyType = currencyType;
        }

        public String getCurrencyType() {
            return currencyType;
        }

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

    }