package com.quseit.payapp.bussiness.devicesetting;

import com.quseit.dev.HttpCode;
import com.quseit.dev.ObserverHandler;
import com.quseit.payapp.base.BasePresenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;


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
    public void saveToken(final String token) {
        mDeviceSettingModel.active("Bearer " + token, "ACTIVE")
                .flatMap(new Function<ResponseBody, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> apply(ResponseBody response) throws Exception {
                        return mDeviceSettingModel.saveDeciveToken(token);
                    }
                })
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDeviceSettingView.showLoading();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnTerminate(new Action() {
                    @Override
                    public void run() throws Exception {
                        mDeviceSettingView.hideLoading();
                    }
                })
                .subscribe(new ObserverHandler<Boolean>() {
                    @Override
                    public void onResponse(Boolean response) {
                        if (response) {
                            mDeviceSettingView.showDialog("save token success", true);
                        } else {
                            mDeviceSettingView.showDialog("save token fail", false);
                        }
                    }

                    @Override
                    public void onFail(int code) {
                        if (code == HttpCode.UNAUTHORIZED) {
                            mDeviceSettingView.showDialog("illegal token", false);
                        }
                        if (code == -1)
                            mDeviceSettingView.showDialog("save token fail", false);
                    }
                });

    }
}
