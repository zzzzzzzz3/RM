package com.quseit.payapp.base;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.quseit.payapp.BuildConfig;
import com.quseit.payapp.Http.Api;
import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.SoundPoolUtil;

/**
 * 文 件 名: BaseApplication
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 12:30
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RetrofitManager.getInstance()
                .setTimeOut(15)
                .openDebug(BuildConfig.DEBUG)
                .supportSSL()
                .init(Api.BASE_URL);
        PreferenceUtil.getInstance().init(this);
        SoundPoolUtil.getInstance().init(this);
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
    }
}
