package com.quseit.payapp.bussiness.devicesetting;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.TerminalService;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.request.ActiveRequestBean;
import com.quseit.payapp.bean.request.TokenRequest;
import com.quseit.payapp.bean.response.TokenBean;
import com.quseit.payapp.util.DataStore2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import okhttp3.ResponseBody;

/**
 * 文 件 名: DeviceSettingModelImpl
 * 创 建 人: ZhangRonghua
 * 创建日期:
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class DeviceSettingModelImpl implements DeviceSettingContract.DeviceSettingModel {

    @Override
    public Observable<Boolean> saveDeciveToken(final String token) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e){
                try {
                    DataStore2.getInstance().save(GlobalBean.DECIVE_TOKEN,token);
                    if (DataStore2.getInstance().getData(GlobalBean.DECIVE_TOKEN)!=null){
                        e.onNext(Boolean.TRUE);
                        e.onComplete();
                    }else {
                        e.onNext(Boolean.FALSE);
                        e.onComplete();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onError(new Throwable("save token fail"));
                }

            }
        });
    }

    @Override
    public Observable<Boolean> saveRefreshAndAccessToken(final String refreshToken, final String accessToken,final int expires) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            @Override
            public void subscribe(ObservableEmitter<Boolean> e){
                try {
                    DataStore2.getInstance().save(GlobalBean.DECIVE_TOKEN,accessToken);
                    DataStore2.getInstance().save(GlobalBean.REFRESH_TOKEN,refreshToken);
                    DataStore2.getInstance().saveInt(GlobalBean.TOKEN_EXPIRES,expires);
                    if (DataStore2.getInstance().getData(GlobalBean.DECIVE_TOKEN)!=null && DataStore2.getInstance().getData(GlobalBean.REFRESH_TOKEN)!=null){
                        e.onNext(Boolean.TRUE);
                        e.onComplete();
                    }else {
                        e.onNext(Boolean.FALSE);
                        e.onComplete();
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                    e.onError(new Throwable("save token fail"));
                }

            }
        });
    }

    @Override
    public Observable<ResponseBody> active(String token, String type) {
        return RetrofitManager.getInstance().createService(TerminalService.class).active(token,new ActiveRequestBean(type));
    }

    @Override
    public Observable<TokenBean> refreshToken(String token) {
        return RetrofitManager.getInstance().createService(TerminalService.class).refreshToken(new TokenRequest(token));
    }
}