package com.quseit.payapp.bean.response;


/**
 * 文 件 名: PayResponseV3
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 17:28
 * 修改时间：
 * 修改备注：
 */

public class PayResponseV3 {
    private Data item;
    private String code;
    public void setData(Data item) {
        this.item = item;
    }
    public Data getData() {
        return item;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public class GeoLocation {

        private int latitude;
        private int longitude;
        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }
        public int getLatitude() {
            return latitude;
        }

        public void setLongitude(int longitude) {
            this.longitude = longitude;
        }
        public int getLongitude() {
            return longitude;
        }

    }

    public class Store {

        private String id;
        private String name;
        private String addressLine1;
        private String addressLine2;
        private String postCode;
        private String city;
        private String state;
        private String country;
        private String countryCode;
        private String phoneNumber;
        private GeoLocation geoLocation;
        private String status;
        private String createdAt;
        private String updatedAt;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }
        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }
        public String getAddressLine2() {
            return addressLine2;
        }

        public void setPostCode(String postCode) {
            this.postCode = postCode;
        }
        public String getPostCode() {
            return postCode;
        }

        public void setCity(String city) {
            this.city = city;
        }
        public String getCity() {
            return city;
        }

        public void setState(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }

        public void setCountry(String country) {
            this.country = country;
        }
        public String getCountry() {
            return country;
        }

        public void setCountryCode(String countryCode) {
            this.countryCode = countryCode;
        }
        public String getCountryCode() {
            return countryCode;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setGeoLocation(GeoLocation geoLocation) {
            this.geoLocation = geoLocation;
        }
        public GeoLocation getGeoLocation() {
            return geoLocation;
        }

        public void setStatus(String status) {
            this.status = status;
        }
        public String getStatus() {
            return status;
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

    }

    public class Order {

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

    public class Data {

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

    }

    public class Error {

        private String code;
        private String message;
        public void setCode(String code) {
            this.code = code;
        }
        public String getCode() {
            return code;
        }

        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage() {
            return message;
        }

    }
}
