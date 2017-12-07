package com.quseit.payapp.bussiness.transations;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.Filter;
import com.quseit.payapp.bean.request.TransationsRequest;
import com.quseit.payapp.bean.response.TransationResponse;

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
        loadMore(show);
    }

    @Override
    public void loadMore(boolean show) {
        if (!mTransationsRequest.getCursor().equals(GlobalBean.NO_DATA)) {
            logic(mTransationsModel.getTransations(mTransationsRequest), show, new ObserverHandler<TransationResponse>() {
                @Override
                public void onResponse(TransationResponse response) {
                    if (mTransationsRequest.getCursor().equals("")) {
                        mTransationsView.addDataToList(response.getItems());
                    } else {
                        mTransationsView.loadMore(response.getItems());
                    }
                    if (response.getCount() > 0) {
                        mTransationsRequest.setCursor(response.getCursor());
                    } else {
                        mTransationsRequest.setCursor(GlobalBean.NO_DATA);
                    }
                }

                @Override
                public void onFail(int code,String msg) {
                    if (code == HttpCode.UNAUTHORIZED) {
                        mTransationsView.setUpToken();
                    } else {
                        mTransationsView.showDialog(msg,false);
                    }
                }
            });
        }else {
            mView.hideLoading();
        }
    }
}
