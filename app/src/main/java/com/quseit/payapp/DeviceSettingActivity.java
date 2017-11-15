package com.quseit.payapp;

import android.content.Intent;
import android.widget.EditText;

import com.quseit.pay.PrintUtil;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bussiness.main.MainActivity;
import com.quseit.payapp.util.PermissionUtil;
import com.quseit.payapp.util.PreferenceUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: DeviceSettingActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 17:25
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DeviceSettingActivity extends BaseActivity {

    @BindView(R.id.device_token_edit)
    EditText mEditText;

    @Override
    public int getRootView() {
        return R.layout.activity_device_setting;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "DeviceSetting";
    }

    @OnClick(R.id.btn_ok)
    public void saveDeviceToken(){
        String token = mEditText.getText().toString();
        if (token.equals("")){
            toast("device token is illegal");
            return;
        }
        PreferenceUtil.getInstance().saveStr("device_token",token);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
