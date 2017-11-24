package com.quseit.payapp.bussiness.membership.points;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.PointsRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;

/**
 * 文 件 名: PointsPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PointsPresenterImpl extends BasePresenter implements PointsContract.PointsPresenter {

    private PointsContract.PointsView mPointsView;
    private PointsContract.PointsModel mPointsModel;

    public PointsPresenterImpl(PointsContract.PointsView view) {
        mPointsView = view;
        setView(view);
        mPointsModel = new PointsModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void givePoints(int amount, String mobile, String countryCode, String type) {
        if (checkData(amount, mobile, countryCode, type)) {
            logic(mPointsModel.givePoints(new PointsRequestBean(amount, type, mobile, countryCode)), new ObserverHandler<ResponseBean>() {
                @Override
                public void onResponse(ResponseBean response) {
                    if (response.success()) {
                        mPointsView.showDialog(response.getMsg(), true);
                    } else {
                        mPointsView.showDialog(response.getMsg(), false);
                    }
                }

                @Override
                public void onFail(int code) {
                    if (code== HttpCode.UNAUTHORIZED){
                        mPointsView.setUpToken();
                    }else {
                        mPointsView.showDialog("net error", false);
                    }
                }
            });
        }
    }

    private boolean checkData(int amount, String mobile, String countryCode, String type) {
        if (amount <= 0) {
            mPointsView.showMessage("Minimum amount is 1");
            return false;
        }
        if (mobile.equals("")) {
            mPointsView.showMessage("mobile is empty");
            return false;
        }
        if (countryCode.equals("")) {
            mPointsView.showMessage("country code is empty");
            return false;
        }
        if (type.equals("")) {
            mPointsView.showMessage("type is empty");
            return false;
        }
        return true;
    }
}
