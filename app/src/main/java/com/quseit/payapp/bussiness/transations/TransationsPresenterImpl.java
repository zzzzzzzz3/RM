package com.quseit.payapp.bussiness.transations;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Filter;
import com.quseit.payapp.bean.request.TransationsRequest;
import com.quseit.payapp.bean.response.TransationResponse;
import com.quseit.payapp.bean.response.TransationResponseV3;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;

/**
 * 文 件 名: TransationsPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationsPresenterImpl extends BasePresenter implements TransationsContract.TransationsPresenter {

    private TransationsContract.TransationsView mTransationsView;
    private TransationsContract.TransationsModel mTransationsModel;
    private TransationsRequest mTransationsRequest;
    private String cursor = "";

    public TransationsPresenterImpl(TransationsContract.TransationsView view) {
        mTransationsView = view;
        setView(view);
        mTransationsModel = new TransationsModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void getTransation(String startAt,String endAt,boolean filter,boolean show) {
        mTransationsRequest = new TransationsRequest("",startAt,endAt,new Filter(filter?"REFUNDED":""));
    }

    @Override
    public void getTransationV3(String startAt, String endAt, boolean refund, boolean show) {
        cursor = "";
        String filter = "{\"transactionAt\":{\"$gte\":\""+startAt+"\",\"$lte\":\""+endAt+"\"}"+(refund?",\"status\":\"FULL_REFUNDED\"}":"}");
        logic(mTransationsModel.getTransationsV3(filter,cursor), show, new ObserverHandler<TransationResponseV3>() {
            @Override
            public void onResponse(TransationResponseV3 response) {
                if(response.getCode().equals("SUCCESS")){
                    mTransationsView.addDataToListV3(response.getItems());
                    cursor = response.getCursor();
                }
            }

            @Override
            public void onFail(int code, String msg) {
                if (code == HttpCode.UNAUTHORIZED) {
                    mTransationsView.setUpToken();
                }
            }
        });
    }

    @Override
    public void loadMore(String startAt, String endAt, boolean refund, boolean show) {
        if (cursor!=null) {
            String filter = "{\"transactionAt\":{\"$gte\":\""+startAt+"\",\"$lte\":\""+endAt+"\"}"+(refund?",\"status\":\"FULL_REFUNDED\"}":"}");
            logic(mTransationsModel.getTransationsV3(filter,cursor), show, new ObserverHandler<TransationResponseV3>() {
                @Override
                public void onResponse(TransationResponseV3 response) {
                    if(response.getCode().equals("SUCCESS")){
                        mTransationsView.loadMore(response.getItems());
                        cursor = response.getCursor();
                    }
                }

                @Override
                public void onFail(int code, String msg) {
                    if (code == HttpCode.UNAUTHORIZED) {
                        mTransationsView.setUpToken();
                    }
                }
            });

        }else {
            mView.hideLoading();
        }
    }

    @Override
    public void getTransactionById(String id) {

        if(id.equals("")){
            mTransationsView.showMessage("Transaction not found");
            return;
        }

        logic(mTransationsModel.getTransactionById(id), true, new ObserverHandler<PayResponseV3>() {
            @Override
            public void onResponse(PayResponseV3 response) {
                mTransationsView.toOrderDetail(response.getData());
            }

            @Override
            public void onFail(int code, String msg) {
                if (code == HttpCode.UNAUTHORIZED) {
                    mTransationsView.setUpToken();
                } else {
                    mTransationsView.showMessage("Transaction not found");
                }
            }
        });
    }
}
