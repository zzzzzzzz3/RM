package com.quseit.payapp.bussiness.voucher.redeem;

import com.quseit.payapp.base.BasePresenter;

/**
 * 文 件 名: RedeemPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RedeemPresenterImpl extends BasePresenter implements RedeemContract.RedeemPresenter {

    private RedeemContract.RedeemView mRedeemView;
    private RedeemContract.RedeemModel mRedeemModel;

    public RedeemPresenterImpl(RedeemContract.RedeemView view) {
        mRedeemView = view;
        setView(view);
        mRedeemModel = new RedeemModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void redeem(String code) {

    }
}
