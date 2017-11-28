package com.quseit.payapp.bean.response;

public class Expiry {
    private int day;

    private String expiredAt;

    private String type;

    public void setDay(int day) {
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

    public void setExpiredAt(String expiredAt) {
        this.expiredAt = expiredAt;
    }

    public String getExpiredAt() {
        return this.expiredAt;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

}