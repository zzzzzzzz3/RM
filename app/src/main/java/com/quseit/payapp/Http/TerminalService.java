package com.quseit.payapp.Http;

import com.quseit.payapp.bean.request.ActiveRequestBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * 文 件 名: TerminalService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/28 12:02
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface TerminalService {

    @POST("active")
    Observable<ResponseBody> active(@Header("Authorization") String token, @Body ActiveRequestBean bean);
}
