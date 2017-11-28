package com.quseit.payapp.bussiness.membership.signup;

import com.quseit.payapp.bean.request.MemberRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 文 件 名: SignUpContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface SignUpContract {

    interface SignUpView extends IView {
        void showDialog(String msg,boolean success);
    }

    interface SignUpModel extends IModel {
        Observable<ResponseBody> signUp(MemberRequestBean bean);
    }

    interface SignUpPresenter extends IPresenter {
        void signUp(String name,String mobile,String countryCode,String email);
    }
}
