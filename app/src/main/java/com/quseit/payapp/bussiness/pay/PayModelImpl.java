package com.quseit.payapp.bussiness.pay;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.PaymentService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.BaseResponse;
import com.quseit.payapp.bean.response.PayResponseBean;

import io.reactivex.Observable;

/**
 * 文 件 名: PayModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/14 10:34
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PayModelImpl extends BaseModel implements PayContract.PayModel {

    @Override
    public Observable<BaseResponse<PayResponseBean>> pay(PayRequestBean payRequestBean) {
        return RetrofitManager.getInstance().createService(PaymentService.class).pay(mToken,payRequestBean);
    }
}
