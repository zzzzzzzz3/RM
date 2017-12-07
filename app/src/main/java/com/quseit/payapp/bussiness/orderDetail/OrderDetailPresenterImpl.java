package com.quseit.payapp.bussiness.orderDetail;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.response.UserBean;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * 文 件 名: OrderDetailPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 00:14
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class OrderDetailPresenterImpl extends BasePresenter implements OrderDetailContract.OrderDetailPresenter {

    private OrderDetailContract.OrderDetailModle mOrderDetailModle;
    private OrderDetailContract.OrderDetailView mOrderDetailView;

    public OrderDetailPresenterImpl(OrderDetailContract.OrderDetailView view) {
        setView(view);
        mOrderDetailModle = new OrderDetailModleImpl();
        mOrderDetailView = view;
    }


    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void getUsers() {
        logic(mOrderDetailModle.getUsers(), false, new ObserverHandler<List<UserBean>>() {
            @Override
            public void onResponse(List<UserBean> response) {
                mOrderDetailView.showRefundDialog(response);
            }

            @Override
            public void onFail(int code, String msg) {
                mOrderDetailView.showDialog("Invalid operation", false);
            }
        });
    }

    @Override
    public void refund(String pin, String key, String orderId, String reason) {
        if (checkdata(pin, key, orderId, reason)) {

            logic(mOrderDetailModle.refund(pin, key, orderId, reason), true, new ObserverHandler<ResponseBody>() {
                @Override
                public void onResponse(ResponseBody response) {
                    mOrderDetailView.showDialog("Success", true);
                }

                @Override
                public void onFail(int code, String msg) {
                    if (code == HttpCode.UNAUTHORIZED) {
                        mOrderDetailView.setUpToken();
                    } else {
                        mOrderDetailView.showDialog(msg, false);
                    }
                }
            });
        }
    }

    private boolean checkdata(String pin, String key, String orderId, String reason) {
        if (pin.isEmpty()) {
            mView.showMessage("pin is empty");
            return false;
        }
        if (reason.isEmpty()) {
            mView.showMessage("reason is empty");
            return false;
        }
        if (orderId.isEmpty()) {
            mView.showMessage("order id is empty");
            return false;
        }
        if (key == null) {
            mView.showMessage("no permission");
            return false;
        }
        return true;
    }
}
