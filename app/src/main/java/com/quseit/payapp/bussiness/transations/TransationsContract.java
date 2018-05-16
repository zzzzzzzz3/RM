package com.quseit.payapp.bussiness.transations;

import com.quseit.payapp.bean.request.TransationsRequest;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.bean.response.TransationBean;
import com.quseit.payapp.bean.response.TransationResponse;
import com.quseit.payapp.bean.response.TransationResponseV3;
import com.quseit.payapp.bean.response.pay_v3.Transaction;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import java.util.List;

import io.reactivex.Observable;

/**
 * 文 件 名: TransationsContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface TransationsContract {

    interface TransationsView extends IView {
        void addDataToList(List<TransationBean> data);
        void addDataToListV3(List<Transaction> data);
        void loadMore(List<TransationBean> data);
        void showDialog(String msg,boolean success);
        void toOrderDetail(Transaction transaction);
    }

    interface TransationsModel extends IModel {
        Observable<TransationResponse> getTransations(TransationsRequest request);
        Observable<TransationResponseV3> getTransationsV3(String filters);
        Observable<PayResponseV3> getTransactionById(String id);
    }

    interface TransationsPresenter extends IPresenter {
        void getTransation(String startAt,String endAt,boolean filter,boolean show);
        void getTransationV3(String startAt,String endAt,boolean filter,boolean show);
        void loadMore(boolean show);
        void getTransactionById(String id);
    }
}
