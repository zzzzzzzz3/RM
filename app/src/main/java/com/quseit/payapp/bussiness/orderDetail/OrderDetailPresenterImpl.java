package com.quseit.payapp.bussiness.orderDetail;

import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.response.UserBean;

import java.util.List;

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

    public OrderDetailPresenterImpl(OrderDetailContract.OrderDetailView view){
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
                mOrderDetailView.showDialog("Invalid operation",false);
            }
        });
    }
}
