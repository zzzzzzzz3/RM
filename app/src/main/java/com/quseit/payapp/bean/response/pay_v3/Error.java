package com.quseit.payapp.bean.response.pay_v3;

import java.io.Serializable;

public class Error implements Serializable{

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