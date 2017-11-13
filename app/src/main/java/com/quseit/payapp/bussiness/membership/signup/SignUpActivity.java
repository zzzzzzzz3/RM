package com.quseit.payapp.bussiness.membership.signup;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;

import butterknife.BindView;

/**
 * 文 件 名: SignUpActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 12:20
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.name_edit)
    EditText nameEdit;
    @BindView(R.id.eamil_edit)
    EditText emailEdit;
    @BindView(R.id.mobile_edit)
    EditText mobileEdit;
    @BindView(R.id.toolbar_right_tv)
    TextView doneTv;

    @Override
    public int getRootView() {
        return R.layout.activity_sign_up;
    }

    @Override
    public void initView() {
        doneTv.setText("Done");
        doneTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Sign Up";
    }
}
