package com.quseit.payapp.bean.response.pay_v3;

import java.io.Serializable;

public class GeoLocation implements Serializable{

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