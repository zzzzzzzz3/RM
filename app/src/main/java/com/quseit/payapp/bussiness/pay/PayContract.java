package com.quseit.payapp.bussiness.pay;

import com.quseit.pay.PayInfoBean;
import com.quseit.pay.PayInfoBeanV3;
import com.quseit.payapp.bean.request.Member;
import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.request.pay_v3.PayRequestV3;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.bean.response.PayResponseV3;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;


/**
 * 文 件 名: PayContract
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/14 10:28
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface PayContract {

    interface PayView extends IView{
        void printPayInfo(PayInfoBean payInfo);
        void printPayInfoV3(PayInfoBeanV3 payInfo);
        void showDialog(String msg,boolean isSuccess);
    }

    interface PayModel extends IModel{
        Observable<PayResponseBean> pay(PayRequestBean payRequestBean);
        Observable<PayResponseV3> quickPay(PayRequestV3 payRequestV3);
    }

    interface PayPresenter extends IPresenter{
        void pay(String Amount, String AuthCode, String Remark, String StoreID, Member member);
        void qiuckPay(String Amount, String AuthCode, String Remark, String StoreID, Member member);
        void printLastReceipt();
    }
}
