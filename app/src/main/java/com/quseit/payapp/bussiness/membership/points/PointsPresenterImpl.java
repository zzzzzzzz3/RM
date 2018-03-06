package com.quseit.payapp.bussiness.membership.points;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.PointsRequestBean;

import okhttp3.ResponseBody;

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
    public void givePoints(int amount, final String mobile, String countryCode, String type, final long memberId) {
        if (checkData(amount, mobile, countryCode, type, memberId)) {
            logic(mPointsModel.givePoints(new PointsRequestBean(amount, type, mobile, countryCode, memberId)), new ObserverHandler<ResponseBody>() {
                @Override
                public void onResponse(ResponseBody response) {
                    if (memberId == 0) {
                        mPointsView.showDialog(mobile + " has earned:", true);
                    } else {
                        mPointsView.showDialog(memberId + " has earned:", true);
                    }
                }

                @Override
                public void onFail(int code, String msg) {
                    if (code == HttpCode.UNAUTHORIZED) {
                        mPointsView.setUpToken();
                    } else {
                        mPointsView.showDialog(msg, false);
                    }
                }
            });
        }
    }

    private boolean checkData(int amount, String mobile, String countryCode, String type, long memberId) {
        if (amount <= 0) {
            mPointsView.showMessage("Points is minimum 1");
            return false;
        }
        if (type.equals(PointsRequestBean.PHONENUMBER)) {
            if (mobile.equals("")) {
                mPointsView.showMessage("mobile is empty");
                return false;
            }
            if (countryCode.equals("")) {
                mPointsView.showMessage("country code is empty");
                return false;
            }
        } else {
            if (memberId <= 0) {
                mPointsView.showMessage("memberId is empty");
                return false;
            }
        }

        return true;
    }
}
