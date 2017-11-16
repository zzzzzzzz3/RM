package com.quseit.payapp.bussiness.voucher.redeem;

import com.quseit.payapp.bean.response.ResponseBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;
import com.quseit.payapp.widget.RMProgressDialog;

import io.reactivex.Observable;

/**
 * 文 件 名: RedeemContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface RedeemContract {

    interface RedeemView extends IView {
        void changeDialogState(String msg, RMProgressDialog.TYPE type);
    }

    interface RedeemModel extends IModel {
        Observable<ResponseBean> redeem(String code);
    }

    interface RedeemPresenter extends IPresenter {
        void redeem(String code);
    }
}
