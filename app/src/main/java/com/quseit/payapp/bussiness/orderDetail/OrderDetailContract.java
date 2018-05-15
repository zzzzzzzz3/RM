package com.quseit.payapp.bussiness.orderDetail;

import com.quseit.payapp.bean.request.RefundRequest;
import com.quseit.payapp.bean.request.RefundRequestV3;
import com.quseit.payapp.bean.response.UserBean;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 文 件 名: OrderDetailContract
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 00:08
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface OrderDetailContract {

    interface OrderDetailView extends IView{
        void showRefundDialog(List<UserBean> list);
        void showDialog(String msg,boolean success);
        void setOrderInfo(PayResponseV3 info);
    }

    interface OrderDetailModle extends IModel{
        Observable<List<UserBean>> getUsers();
        Observable<ResponseBody> refund(String pin,String key,String orderId,String reason);
        Observable<PayResponseV3> refundV3(RefundRequestV3 requestV3);
    }

    interface OrderDetailPresenter extends IPresenter{
        void getUsers();
        void refund(String pin,String key,String orderId,String reason);
        void refundV3(int amount,String email,String transactionId);
    }




}
