package com.quseit.payapp.network;

import com.quseit.payapp.bean.PayBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by sky on 2017/3/20.
 */

interface ServiceRequest {
    @FormUrlEncoded
    @POST("/pay")
    Observable<PayBean> pay(
            @Field("email") String email,
            @Field("pwd") String pwd
    );

}
