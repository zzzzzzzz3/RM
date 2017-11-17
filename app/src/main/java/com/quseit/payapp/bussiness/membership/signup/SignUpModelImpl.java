package com.quseit.payapp.bussiness.membership.signup;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.MemberService;
import com.quseit.payapp.base.BaseModel;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.MemberRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;
import com.quseit.payapp.util.DataStore2;

import io.reactivex.Observable;

/**
 * 文 件 名: SignUpModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SignUpModelImpl extends BaseModel implements SignUpContract.SignUpModel {

    @Override
    public Observable<ResponseBean> signUp(MemberRequestBean bean) {
        return RetrofitManager.getInstance().createService(MemberService.class).signUp(mToken,bean);
    }
}