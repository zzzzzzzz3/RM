package com.quseit.payapp.bussiness.voucher.issue;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.request.VoucherRequestBean;
import com.quseit.payapp.bean.response.VoucherResponse;

/**
 * 文 件 名: IssuePresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class IssuePresenterImpl extends BasePresenter implements IssueContract.IssuePresenter {

    private IssueContract.IssueView mIssueView;
    private IssueContract.IssueModel mIssueModel;
    private String cursor = "";

    public IssuePresenterImpl(IssueContract.IssueView view) {
        mIssueView = view;
        setView(view);
        mIssueModel = new IssueModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void getVouchers() {
            cursor = "";
            loadMore();
    }

    @Override
    public void loadMore() {
        if (!cursor.equals(GlobalBean.NO_DATA)){
            logic(mIssueModel.getVouchers(cursor), new ObserverHandler<VoucherResponse>() {
                @Override
                public void onResponse(VoucherResponse response) {
                    if (cursor.equals("")){
                        mIssueView.setDataToList(response.getItems());
                    }else {
                        mIssueView.loadMore(response.getItems());
                    }
                    if (response.getCount()>0) {
                        cursor = response.getCursor();
                    } else {
                        cursor = GlobalBean.NO_DATA;
                    }
                }

                @Override
                public void onFail(int code) {
                    if (code== HttpCode.UNAUTHORIZED){
                        mIssueView.setUpToken();
                    }else {
                        mIssueView.showDialog("net error", false);
                    }
                }
            });
        }else {
            mView.hideLoading();
        }
    }
}
