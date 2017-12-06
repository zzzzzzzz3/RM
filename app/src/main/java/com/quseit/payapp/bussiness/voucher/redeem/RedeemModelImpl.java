package com.quseit.payapp.bussiness.voucher.redeem;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.VoucherService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.RequestBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 文 件 名: RedeemModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RedeemModelImpl extends BaseModel implements RedeemContract.RedeemModel {

    @Override
    public Observable<ResponseBody> redeem(String code) {
        return RetrofitManager.getInstance().createService(VoucherService.class).redeemVoucher(mToken,code,new RequestBean(""));
    }
}