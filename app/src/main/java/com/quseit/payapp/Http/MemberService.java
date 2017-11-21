package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.MemberRequestBean;
import com.quseit.payapp.bean.request.PointsRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * 文 件 名: MemberService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/17 14:41
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface MemberService {

    @PUT("member")
    Observable<ResponseBean> signUp(@Header("Authorization") String token, @Body MemberRequestBean bean);

    @PUT("loyalty/point/spending")
    Observable<ResponseBean> givePoints(@Header("Authorization") String token, @Body PointsRequestBean bean);
}
