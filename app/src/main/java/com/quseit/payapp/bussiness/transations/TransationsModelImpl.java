package com.quseit.payapp.bussiness.transations;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.TransationService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.response.TransationResponse;

import io.reactivex.Observable;

/**
 * 文 件 名: TransationsModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationsModelImpl extends BaseModel implements TransationsContract.TransationsModel {

    @Override
    public Observable<TransationResponse> getTransations() {
        return RetrofitManager.getInstance().createService(TransationService.class).getTransations(new RequestBean());
    }
}