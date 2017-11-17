package com.quseit.payapp.bussiness.membership.points;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.MemberService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.request.MemberRequestBean;
import com.quseit.payapp.bean.request.PointsRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;

import io.reactivex.Observable;

/**
 * 文 件 名: PointsModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PointsModelImpl extends BaseModel implements PointsContract.PointsModel {

    @Override
    public Observable<ResponseBean> givePoints(PointsRequestBean bean) {

        return RetrofitManager.getInstance().createService(MemberService.class).givePoints(mToken,bean);
    }
}