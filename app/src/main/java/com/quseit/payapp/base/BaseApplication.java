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
import com.quseit.payapp.db.GreenDaoHelper;
import com.quseit.payapp.util.DataStore2;
import com.quseit.payapp.util.DynamicTimeFormat;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.SoundPoolUtil;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.MyFooter;
import com.quseit.payapp.widget.MyHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.DefaultRefreshFooterCreater;
import com.scwang.smartrefresh.layout.api.DefaultRefreshHeaderCreater;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    static {
        SmartRefreshLayout.setDefaultRefreshHeaderCreater(new DefaultRefreshHeaderCreater() {
            @Override
            public RefreshHeader createRefreshHeader(Context context, RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.window_bg_color, android.R.color.black);
                return new MyHeader(context).setTimeFormat(new SimpleDateFormat("'Last Update' M-d HH:mm"));
            }
        });
        SmartRefreshLayout.setDefaultRefreshFooterCreater(new DefaultRefreshFooterCreater() {
            @Override
            public RefreshFooter createRefreshFooter(Context context, RefreshLayout layout) {
                return new MyFooter(context).setDrawableSize(20);
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Map<String,String> headers = new HashMap<>();
        headers.put("X-Rm-Platform","application/terminal");
        RetrofitManager.getInstance()
                .setTimeOut(15)
                .openDebug(BuildConfig.DEBUG)
                .supportSSL()
                .addHeaders(headers)
                .init(Api.BASE_URL);
        PreferenceUtil.getInstance().init(this);
        SoundPoolUtil.getInstance().init(this);
        DataStore2.getInstance().init(getApplicationContext());
        UIUtil.init(this.getApplicationContext());
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this);
        }
        GreenDaoHelper.getInstance().init(this);
    }
}
