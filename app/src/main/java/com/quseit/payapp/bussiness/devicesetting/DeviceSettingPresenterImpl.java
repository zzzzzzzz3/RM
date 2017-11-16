package com.quseit.payapp.bussiness.devicesetting;

import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;

/**
 * 文 件 名: DeviceSettingPresenterImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DeviceSettingPresenterImpl extends BasePresenter implements DeviceSettingContract.DeviceSettingPresenter {

    private DeviceSettingContract.DeviceSettingView mDeviceSettingView;
    private DeviceSettingContract.DeviceSettingModel mDeviceSettingModel;

    public DeviceSettingPresenterImpl(DeviceSettingContract.DeviceSettingView view) {
        mDeviceSettingView = view;
        setView(view);
        mDeviceSettingModel = new DeviceSettingModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }

    @Override
    public void saveToken(String token) {
        logic(mDeviceSettingModel.saveDeciveToken(token), new ObserverHandler<Boolean>() {
            @Override
            public void onResponse(Boolean response) {
                if (response){
                    mDeviceSettingView.changeDialogState("save token fail",true);
                }else {
                    mDeviceSettingView.changeDialogState("save token fail",false);
                }
            }

            @Override
            public void onFail() {
                mDeviceSettingView.changeDialogState("save token fail",false);
            }
        });
    }
}
