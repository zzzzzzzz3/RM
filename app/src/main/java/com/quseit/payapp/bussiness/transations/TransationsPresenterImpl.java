package com.quseit.payapp.bussiness.transations;

import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
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

    private boolean isFirst = false;

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
    public void getTransation() {
        logic(mTransationsModel.getTransations(), isFirst,new ObserverHandler<TransationResponse>() {
            @Override
            public void onResponse(TransationResponse response) {
                mTransationsView.addDataToList(response.getItems());
                isFirst = false;
            }

            @Override
            public void onFail() {
                mTransationsView.showMessage("net error");
                isFirst = false;
            }
        });
    }
}
