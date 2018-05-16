package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.request.RefundRequestV3;
import com.quseit.payapp.bean.request.pay_v3.PayRequestV3;
import com.quseit.payapp.bean.request.RefundRequest;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.bean.response.QRResponseBean;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.bean.response.refund_users.RefundUsers;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

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
    Observable<QRResponseBean> getQr(@Header("Authorization") String token, @Body PayRequestBean payRequestBean);

    @POST("payment/{key}/refund")
    Observable<ResponseBody> refund(@Header("Authorization") String token, @Header("X-Rm-Pin") String pin, @Path("key") String key, @Body RefundRequest request);

    @POST("payment/transaction/refund")
    Observable<PayResponseV3> refundV3(@Header("Authorization") String token, @Header("X-Rm-Pin") String pin, @Body RefundRequestV3 requestV3);

    @POST("payment/quickpay")
    Observable<PayResponseV3> quickPay(@Header("Authorization") String token, @Body PayRequestV3 payRequestBean);

    @GET("payment/refund/users")
    Observable<RefundUsers> getRefoundUsers(@Header("Authorization") String token);

}
