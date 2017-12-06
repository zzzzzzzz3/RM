package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.response.MerchantBean;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 文 件 名: MerchantService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/5 11:08
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface MerchantService {

    /**
     * @desc 获取商户信息
     * */
    @POST("merchant/users")
    Observable<MerchantBean> getMerchantInfo(@Header("Authorization") String token, @Body RequestBean requestBean);
}
