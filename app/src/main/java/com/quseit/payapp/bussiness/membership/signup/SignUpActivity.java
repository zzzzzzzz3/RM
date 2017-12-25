package com.quseit.payapp.bussiness.membership.signup;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: SignUpActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 12:20
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SignUpActivity extends BaseActivity implements SignUpContract.SignUpView{

    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.eamil_edit)
    EditText emailEdit;
    @BindView(R.id.mobile_edit)
    EditText mobileEdit;
    @BindView(R.id.country_code_tv)
    TextView countryCodeTv;

    private SignUpContract.SignUpPresenter mSignUpPresenter;
    private RMProgressDialog mRMProgressDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void initView() {
        setRightText("Done", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdit.getText().toString();
                String mobile = mobileEdit.getText().toString();
                String countryCode = countryCodeTv.getText().toString();
                String email = emailEdit.getText().toString();

                mSignUpPresenter.signUp(name,mobile,countryCode,email);
            }
        });
        mRMProgressDialog = new RMProgressDialog(this);
    }

    @Override
    public void initData() {
        mSignUpPresenter = new SignUpPresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Sign Up";
    }

    @OnClick(R.id.country_code_layout)
    public void countryCode(){
        
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        toast(message);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void setUpToken() {
        settingToken();
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success){
            DialogManager.successDialog(this, msg, new RMDialog.OnPositiveClickListener() {
                @Override
                public void onPositiveClick() {
                    finish();
                }
            });
        }else {
            DialogManager.failDialog(this,msg);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSignUpPresenter.onDestroy();
    }
}
