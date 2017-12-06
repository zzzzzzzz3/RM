package com.quseit.payapp.bussiness.membership.signup;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;
import com.quseit.payapp.bean.request.MemberRequestBean;

import okhttp3.ResponseBody;

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
            logic(mSignUpModel.signUp(new MemberRequestBean(email, name, countryCode, mobile)), new ObserverHandler<ResponseBody>() {
                @Override
                public void onResponse(ResponseBody response) {
                    mSignUpView.showDialog("sign up succeed",true);
                }

                @Override
                public void onFail(int code,String msg) {
                    if (code== HttpCode.UNAUTHORIZED){
                        mSignUpView.setUpToken();
                    }else {
                        mSignUpView.showDialog(msg, false);
                    }
                }
            });
        }
    }

    private boolean checkData(String name, String mobile, String countryCode, String email) {
        if (name.equals("")){
            mSignUpView.showMessage("Name is empty");
            return false;
        }
        if (mobile.length()<9){
            mSignUpView.showMessage("Number of digits is not less than 9");
            return false;
        }
        if (countryCode.equals("")){
            mSignUpView.showMessage("Country code is empty");
            return false;
        }
        if (email.equals("")){
            mSignUpView.showMessage("Email is empty");
            return false;
        }
        return true;
    }
}
