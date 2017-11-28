package com.quseit.payapp.bussiness.devicesetting;

import com.quseit.payapp.bean.response.ResponseBean;
import com.quseit.payapp.contract.IModel;
import com.quseit.payapp.contract.IPresenter;
import com.quseit.payapp.contract.IView;

import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * 文 件 名: DeviceSettingContract
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public interface DeviceSettingContract {

    interface DeviceSettingView extends IView {
        void showDialog(String msg,boolean success);
    }

    interface DeviceSettingModel extends IModel {
        Observable<Boolean> saveDeciveToken(String token);
        Observable<ResponseBody> active(String token, String type);
    }

    interface DeviceSettingPresenter extends IPresenter {
        void saveToken(String token);
    }
}
