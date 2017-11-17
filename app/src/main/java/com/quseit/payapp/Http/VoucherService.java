package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.request.VoucherRequestBean;
import com.quseit.payapp.bean.response.VoucherBean;
import com.quseit.payapp.bean.response.VoucherResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 文 件 名: Voucher
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/17 16:39
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface VoucherService {

    @POST("vouchers/list")
    Observable<VoucherResponse> getVouchers(@Header("Authorization") String token, @Body RequestBean bean);
}
