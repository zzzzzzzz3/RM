package com.quseit.payapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingActivity;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingContract;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingPresenterImpl;
import com.quseit.payapp.bussiness.main.MainActivity;
import com.quseit.payapp.service.RefreshTokenService;
import com.quseit.payapp.util.DataStore2;


/**
 * 文 件 名: WelcomeActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 20:27
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class WelcomeActivity extends AppCompatActivity implements DeviceSettingContract.DeviceSettingView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DeviceSettingContract.DeviceSettingPresenter deviceSettingPresenter = new DeviceSettingPresenterImpl(this);
        if (DataStore2.getInstance().hasData(GlobalBean.REFRESH_TOKEN)) {
            deviceSettingPresenter.saveTokenV3(DataStore2.getInstance().getData(GlobalBean.REFRESH_TOKEN));
        } else {
            startActivity(new Intent(WelcomeActivity.this, DeviceSettingActivity.class));
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, RefreshTokenService.class));
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public void setUpToken() {
        startActivity(new Intent(WelcomeActivity.this, DeviceSettingActivity.class));
        finish();
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success){
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        }else {
            startActivity(new Intent(WelcomeActivity.this, DeviceSettingActivity.class));
            finish();
        }
    }
}
