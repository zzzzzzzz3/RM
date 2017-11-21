package com.quseit.payapp.bussiness.pay;

import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;

/**
 * 文 件 名: PayPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/14 10:36
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PayPresenterImpl extends BasePresenter implements PayContract.PayPresenter {

    private PayContract.PayView mPayView;
    private PayContract.PayModel mPayModel;

    public PayPresenterImpl(PayContract.PayView payView) {
        mPayView = payView;
        setView(mPayView);
        mPayModel= new PayModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void pay(String amount, String authCode, String remark, String storeId) {
        logic(mPayModel.pay(new PayRequestBean(10,authCode,remark)), new ObserverHandler<ResponseBean>() {
            @Override
            public void onResponse(ResponseBean response) {
                if (response.success()){
                    mPayView.showDialog(response.getMsg(),true);
                }else {
                    mPayView.showDialog(response.getMsg(),false);
                }
            }

            @Override
            public void onFail() {
                mPayView.showDialog("net error",false);
            }
        });
    }
}
