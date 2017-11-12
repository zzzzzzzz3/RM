package com.quseit.payapp.Http;


import com.quseit.payapp.bean.AccountBean;
import com.quseit.payapp.bean.ResponseBean;
import com.quseit.payapp.bean.VersionBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 文 件 名: CommonService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 11:49
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public interface CommonService {

    /**
     * @param account  账号
     * @param password 密码
     * @desc 登录
     */
    @POST("login")
    @FormUrlEncoded
    Observable<ResponseBean<AccountBean>> login(@Field("username") String account, @Field("password") String password);

    /**
     * @desc 获取服务器apk版本
     * */
    @GET("check/update")
    Observable<ResponseBean<VersionBean>> getVersion();

    /**
     * 扫描支付
     * */
    @POST("pay")
    @FormUrlEncoded
    Observable<ResponseBean> pay(@Field("Amount") String amount,@Field("AuthCode") String authCode,@Field("Remark") String remark,@Field("StoreID") String storeId);
}
