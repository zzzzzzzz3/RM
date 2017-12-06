package com.quseit.payapp.bussiness.orderDetail;

import com.quseit.payapp.bean.response.UserBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import java.util.List;

import io.reactivex.Observable;

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
    }

    interface OrderDetailModle extends IModel{
        Observable<List<UserBean>> getUsers();
    }

    interface OrderDetailPresenter extends IPresenter{
        void getUsers();
    }




}
