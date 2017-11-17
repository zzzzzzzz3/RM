package com.quseit.payapp.bussiness.voucher.issue;

import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
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

            logic(mIssueModel.getVouchers(new RequestBean()), new ObserverHandler<VoucherResponse>() {
                @Override
                public void onResponse(VoucherResponse response) {
                    if (response.getItems() != null) {
                        mIssueView.setDataToList(response.getItems());
                    } else {
                        mIssueView.showDialog("no data", false);
                    }
                }

                @Override
                public void onFail() {
                    mIssueView.showDialog("net error", false);
                }
            });

    }
}
