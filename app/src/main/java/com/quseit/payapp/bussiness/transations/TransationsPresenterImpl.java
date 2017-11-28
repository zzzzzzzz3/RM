package com.quseit.payapp.bussiness.transations;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
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
    public void getTransation() {
        cursor = "";
        loadMore();
    }

    @Override
    public void loadMore() {
        if (!cursor.equals(GlobalBean.NO_DATA)) {
            logic(mTransationsModel.getTransations(cursor), false, new ObserverHandler<TransationResponse>() {
                @Override
                public void onResponse(TransationResponse response) {
                    if (cursor.equals("")) {
                        mTransationsView.addDataToList(response.getItems());
                    } else {
                        mTransationsView.loadMore(response.getItems());
                    }
                    if (response.getCount() > 0) {
                        cursor = response.getCursor();
                    } else {
                        cursor = GlobalBean.NO_DATA;
                    }
                }

                @Override
                public void onFail(int code) {
                    if (code == HttpCode.UNAUTHORIZED) {
                        mTransationsView.setUpToken();
                    } else {
                        mTransationsView.showDialog("net error",false);
                    }
                }
            });
        }else {
            mView.hideLoading();
        }
    }
}
