package com.quseit.payapp.service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingContract;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingPresenterImpl;
import com.quseit.payapp.util.DataStore2;

/**
 * 文 件 名: RefreshTokenService
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/4/12 16:57
 * 修改时间：
 * 修改备注：
 */


public class RefreshTokenService extends Service implements DeviceSettingContract.DeviceSettingView {

    private boolean isStop = false;
    private DeviceSettingContract.DeviceSettingPresenter mDeviceSettingPresenter;


    @Override
    public void onCreate() {
        super.onCreate();
        mDeviceSettingPresenter = new DeviceSettingPresenterImpl(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //定时刷新token
        refreshToken();
        return super.onStartCommand(intent, flags, startId);
    }

    private void refreshToken() {
        Log.d("refresh token", DataStore2.getInstance().getData(GlobalBean.REFRESH_TOKEN));
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isStop){
                    try {
                        long timing = DataStore2.getInstance().getInt(GlobalBean.TOKEN_EXPIRES)*1000;
                        if(timing<0){
                            timing = 7200000;
                        }
                        Thread.sleep(timing);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mDeviceSettingPresenter.saveTokenV3(DataStore2.getInstance().getData(GlobalBean.REFRESH_TOKEN));
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.isStop = true;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
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

    }

    @Override
    public void showDialog(String msg, boolean success) {

    }
}
