package com.quseit.payapp.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.header.ClassicsHeader;

/**
 * 文 件 名: MyHeader
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 18:08
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MyHeader extends ClassicsHeader {
    public MyHeader(Context context) {
        super(context);
        init();
    }

    public MyHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyHeader(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public void init(){
        REFRESH_HEADER_PULLDOWN = "pull down to refresh";
        REFRESH_HEADER_REFRESHING = "refreshing...";
        REFRESH_HEADER_LOADING = "loading...";
        REFRESH_HEADER_RELEASE = "release immediately refreshed";
        REFRESH_HEADER_FINISH = "refresh completed";
        REFRESH_HEADER_FAILED = "refresh failed";
        REFRESH_HEADER_LASTTIME = "last updated M-d HH:mm";
    }
}
