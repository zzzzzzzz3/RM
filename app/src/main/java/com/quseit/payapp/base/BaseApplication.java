package com.quseit.payapp.base;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import com.facebook.stetho.Stetho;
import com.quseit.payapp.BuildConfig;
import com.quseit.payapp.Http.Api;
import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.R;
import com.quseit.payapp.util.DynamicTimeFormat;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.SoundPoolUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

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

    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.themeColor, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
            }
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                //指定为经典Footer，默认是 BallPulseFooter
                return new ClassicsFooter(context).setDrawableSize(20);
            }
        });
    }

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
