package com.quseit.payapp.bussiness.main;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.MerchantService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.response.MerchantBean;

import io.reactivex.Observable;

/**
 * 文 件 名: MainModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MainModelImpl extends BaseModel implements MainContract.MainModel {

    @Override
    public Observable<MerchantBean> getMerchantInfo() {
        return RetrofitManager.getInstance().createService(MerchantService.class).getMerchantInfo(mToken,new RequestBean(""));
    }
}