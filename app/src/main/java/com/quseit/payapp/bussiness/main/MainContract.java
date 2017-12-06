package com.quseit.payapp.bussiness.main;

import com.quseit.payapp.bean.response.MerchantBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;

/**
 * 文 件 名: MainContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface MainContract {

    interface MainView extends IView {
        void setData(MerchantBean bean);
    }

    interface MainModel extends IModel {
        Observable<MerchantBean> getMerchantInfo();
    }

    interface MainPresenter extends IPresenter {
        void getMerchantInfo();
    }
}
