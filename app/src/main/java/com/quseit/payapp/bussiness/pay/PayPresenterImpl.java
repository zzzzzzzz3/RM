package com.quseit.payapp.bussiness.pay;

import android.annotation.SuppressLint;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.pay.PayInfoBean;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.BaseResponse;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.TimeConverterUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
    private PayResponseBean mPayResponseBean;

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
                    printPayInfo(response);
                    mPayResponseBean = response;
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

    @Override
    public void printLastReceipt() {
        if (mPayResponseBean==null){
            mPayView.showMessage("No receipt to print");
        }else {
            printPayInfo(mPayResponseBean);
        }
    }

    private void printPayInfo(PayResponseBean response) {
        PayInfoBean payInfoBean = new PayInfoBean();
        String strDate = TimeConverterUtil.utc2Local(response.getTransactionAt(),"yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'","yyyy-MM-dd HH:mm:ss");
        payInfoBean.setTransactionAt(strDate);
        payInfoBean.setPaymentAmount(response.getPaymentAmount());
        payInfoBean.setPaymentMethod(response.getPaymentMethod());
        payInfoBean.setRemark(response.getRemark());
        payInfoBean.setStoreName(PreferenceUtil.getInstance().getStr(GlobalBean.MERCHANT));
        payInfoBean.setTransactionId(response.getTransactionId());
        payInfoBean.setMessage(response.getMsg());
        String curr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
        payInfoBean.setDate(curr);
        mPayView.printPayInfo(payInfoBean);
    }
}
