package com.quseit.payapp.bussiness.pay;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.CommonService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.ResponseBean;

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
    public Observable<ResponseBean> pay(String Amount, String AuthCode, String Remark, String StoreID) {
        return RetrofitManager.getInstance().createService(CommonService.class).pay(Amount,AuthCode,Remark,StoreID);
    }
}
