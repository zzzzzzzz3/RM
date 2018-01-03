package com.quseit.payapp.bussiness.pay;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.BaseResponse;
import com.quseit.payapp.bean.response.PayResponseBean;

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
    public void pay(String amount, String authCode, String remark, String storeId,Member member) {
        int a = (int) (Float.parseFloat(amount)*100);
        logic(mPayModel.pay(new PayRequestBean(a,authCode,remark,member)), new ObserverHandler<PayResponseBean>() {
            @Override
            public void onResponse(PayResponseBean response) {
                if (response.success()){
                    mPayView.showDialog(response.getMsg(),true);
                }else {
                    mPayView.showDialog(response.getMsg(),false);
                }
            }

            @Override
            public void onFail(int code,String msg) {
                if (code== HttpCode.UNAUTHORIZED){
                    mPayView.setUpToken();
                }else {
                    mPayView.showDialog(msg, false);
                }
            }
        });
    }
}
