package com.quseit.payapp.bussiness.main;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.response.MerchantBean;
import com.quseit.payapp.util.PreferenceUtil;

/**
 * 文 件 名: MainPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MainPresenterImpl extends BasePresenter implements MainContract.MainPresenter {

    private MainContract.MainView mMainView;
    private MainContract.MainModel mMainModel;

    public MainPresenterImpl(MainContract.MainView view) {
        mMainView = view;
        setView(view);
        mMainModel = new MainModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void getMerchantInfo() {
        logic(mMainModel.getMerchantInfo(), false, new ObserverHandler<MerchantBean>() {
            @Override
            public void onResponse(MerchantBean response) {
                mMainView.setData(response);
                PreferenceUtil.getInstance().saveStr(GlobalBean.AVATAR,response.getAvatar());
                PreferenceUtil.getInstance().saveStr(GlobalBean.MERCHANT,response.getMerchant());
            }

            @Override
            public void onFail(int code, String msg) {
                if (code == HttpCode.UNAUTHORIZED){
                    mMainView.setUpToken();
                }else {
                    mMainView.showMessage(msg);
                }
            }
        });
    }
}
