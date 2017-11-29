package com.quseit.payapp.bussiness.voucher.issue;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.VoucherService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.RequestBean;
import com.quseit.payapp.bean.request.VoucherRequestBean;
import com.quseit.payapp.bean.response.VoucherQrcode;
import com.quseit.payapp.bean.response.VoucherResponse;

import io.reactivex.Observable;

/**
 * 文 件 名: IssueModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class IssueModelImpl extends BaseModel implements IssueContract.IssueModel {

    @Override
    public Observable<VoucherResponse> getVouchers(String cursor) {
        return RetrofitManager.getInstance().createService(VoucherService.class).getVouchers(mToken,new RequestBean(cursor));
    }

    @Override
    public Observable<VoucherQrcode> getVoucherCode(String voucherId) {
        return RetrofitManager.getInstance().createService(VoucherService.class).getVoucherQrcode(mToken,voucherId,new RequestBean(""));
    }
}