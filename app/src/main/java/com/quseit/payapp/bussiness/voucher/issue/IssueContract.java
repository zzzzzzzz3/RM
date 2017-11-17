package com.quseit.payapp.bussiness.voucher.issue;

import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.request.VoucherRequestBean;
import com.quseit.payapp.bean.response.VoucherBean;
import com.quseit.payapp.bean.response.VoucherResponse;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import java.util.List;

import io.reactivex.Observable;


/**
 * 文 件 名: IssueContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface IssueContract {

    interface IssueView extends IView {
        void showDialog(String msg,boolean success);
        void setDataToList(List<VoucherBean> data);
    }

    interface IssueModel extends IModel {
        Observable<VoucherResponse> getVouchers(RequestBean bean);
    }

    interface IssuePresenter extends IPresenter {
        void getVouchers();
    }
}
