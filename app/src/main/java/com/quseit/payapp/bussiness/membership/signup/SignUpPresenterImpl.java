package com.quseit.payapp.bussiness.membership.signup;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.MemberRequestBean;
import com.quseit.payapp.bean.response.ResponseBean;

/**
 * 文 件 名: SignUpPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SignUpPresenterImpl extends BasePresenter implements SignUpContract.SignUpPresenter {

    private SignUpContract.SignUpView mSignUpView;
    private SignUpContract.SignUpModel mSignUpModel;

    public SignUpPresenterImpl(SignUpContract.SignUpView view) {
        mSignUpView = view;
        setView(view);
        mSignUpModel = new SignUpModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void signUp(String name,String mobile,String countryCode,String email) {

        if (checkData(name,mobile,countryCode,email)){
            logic(mSignUpModel.signUp(new MemberRequestBean(name, mobile, countryCode, email)), new ObserverHandler<ResponseBean>() {
                @Override
                public void onResponse(ResponseBean response) {
                    if (response.success()){
                        mSignUpView.showDialog(response.getMsg(),true);
                    }else {
                        mSignUpView.showDialog(response.getMsg(),false);
                    }
                }

                @Override
                public void onFail(int code) {
                    if (code== HttpCode.UNAUTHORIZED){
                        mSignUpView.setUpToken();
                    }else {
                        mSignUpView.showDialog("net error", false);
                    }
                }
            });
        }
    }

    private boolean checkData(String name, String mobile, String countryCode, String email) {
        if (name.equals("")){
            mSignUpView.showMessage("name is empty");
            return false;
        }
        if (mobile.equals("")){
            mSignUpView.showMessage("mobile is empty");
            return false;
        }
        if (countryCode.equals("")){
            mSignUpView.showMessage("country code is empty");
            return false;
        }
        if (email.equals("")){
            mSignUpView.showMessage("email is empty");
            return false;
        }
        return true;
    }
}
