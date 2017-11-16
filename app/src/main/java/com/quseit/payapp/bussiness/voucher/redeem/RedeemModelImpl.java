package com.quseit.payapp.bussiness.voucher.redeem;

import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.response.ResponseBean;

import io.reactivex.Observable;

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
    public Observable<ResponseBean> redeem(String code) {
        return null;
    }
}