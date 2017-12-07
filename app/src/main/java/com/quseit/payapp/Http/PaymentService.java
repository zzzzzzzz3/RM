package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.BaseResponse;
import com.quseit.payapp.bean.response.QRResponseBean;
import com.quseit.payapp.bean.response.PayResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 文 件 名: PaymentService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 09:40
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface PaymentService {

    @POST("pay")
    Observable<PayResponseBean> pay(@Header("Authorization") String token, @Body PayRequestBean payRequestBean);

    @POST("qr/precreate")
    Observable<QRResponseBean> getQr(@Header("Authorization") String token,@Body PayRequestBean payRequestBean);

}
