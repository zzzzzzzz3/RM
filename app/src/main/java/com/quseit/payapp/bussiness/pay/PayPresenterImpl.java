package com.quseit.payapp.bussiness.pay;

import android.annotation.SuppressLint;
import android.util.Log;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.pay.PayInfoBean;
import com.quseit.pay.PayInfoBeanV3;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.request.pay_v3.Order;
import com.quseit.payapp.bean.request.pay_v3.PayRequestV3;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.TimeConverterUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private PayResponseV3 mPayResponseV3;

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
    public void qiuckPay(String amount, String authCode, final String remark, String storeID, Member member) {
        PayRequestV3 payRequestV3 = new PayRequestV3();
        payRequestV3.setAuthCode(authCode);
        int a = (int) (Float.parseFloat(amount)*100);
        Order order = new Order(a,"MYR",authCode,"title","desc",remark);
        payRequestV3.setOrder(order);
        logic(mPayModel.quickPay(payRequestV3), true, new ObserverHandler<PayResponseV3>() {
            @Override
            public void onResponse(PayResponseV3 response) {
                Log.d("pay",response.getCode());
                if (response.getCode().equals("SUCCESS")){
                    mPayView.showDialog(response.getCode(),true);
                    printPayInfoV3(response);
                    mPayResponseV3 = response;
                }else {
                    mPayView.showDialog(response.getCode(),false);
                }
            }

            @Override
            public void onFail(int code, String msg) {
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

    @SuppressLint("DefaultLocale")
    private void printPayInfoV3(PayResponseV3 response) {
        PayInfoBeanV3 payInfoBean = new PayInfoBeanV3();
        String strDate = TimeConverterUtil.utc2Local(response.getData().getCreatedAt(),"yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'","yy/MM/dd HH:mm:ss");
        String[] date = strDate.split(" ");
        payInfoBean.setDate(date[0]);
        payInfoBean.setTime(date[1]);
        payInfoBean.setStoreName(response.getData().getStore().getName());
        payInfoBean.setMerchantName("Merchant Name unknow");
//        payInfoBean.setMerchantId(response.getData().getStore().getId());
        payInfoBean.setMerchantId("unknow");
        payInfoBean.setTransactionId(response.getData().getTransactionId());
        payInfoBean.setMethod(response.getData().getMethod());
        payInfoBean.setType(response.getData().getType());
        payInfoBean.setReferenceId(response.getData().getReferenceId());
//        payInfoBean.setTerminalId(response.getData().getStore().getId());
        payInfoBean.setTerminalId("unknow");
        payInfoBean.setAmount(String.format("%.2f",response.getData().getOrder().getAmount()/100.0));
        payInfoBean.setRemark(response.getData().getOrder().getAdditionalData());
        payInfoBean.setApprcode("unknow");

//        String curr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis()));
//        payInfoBean.setDate(curr);
        mPayView.printPayInfoV3(payInfoBean);
    }
}
