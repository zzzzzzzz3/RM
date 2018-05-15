package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.request.TransationsRequest;
import com.quseit.payapp.bean.response.TransationResponse;
import com.quseit.payapp.bean.response.TransationResponseV3;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * 文 件 名: TransationService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/16 10:31
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface TransationService {

    @POST("transactions")
    Observable<TransationResponse> getTransations(@Header("Authorization") String token,@Body TransationsRequest bean);

    @GET("payment/transactions")
    Observable<TransationResponseV3> getTransactionsV3(@Header("Authorization") String token, @Query("filters")String filters);

}
