package com.quseit.payapp.bean.response.pay_v3;

import java.io.Serializable;

public class Store implements Serializable{

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