package com.quseit.payapp.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.quseit.payapp.R;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;

import java.text.SimpleDateFormat;

/**
 * 文 件 名: MyHeader
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/27 10:42
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

    private void init() {
        REFRESH_HEADER_PULLDOWN = getContext().getString(R.string.refresh);
        REFRESH_HEADER_REFRESHING = getContext().getString(R.string.refreshing);
        REFRESH_HEADER_LOADING = getContext().getString(R.string.loading);
        REFRESH_HEADER_RELEASE = getContext().getString(R.string.release_refresh);
        REFRESH_HEADER_FINISH = getContext().getString(R.string.refresh_complete);
        REFRESH_HEADER_FAILED = getContext().getString(R.string.refresh_fail);
        setTimeFormat(new SimpleDateFormat(" M-d HH:mm"));
    }
}
