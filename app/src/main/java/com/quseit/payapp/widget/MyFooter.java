package com.quseit.payapp.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * 文 件 名: MyFooter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/15 18:13
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MyFooter extends ClassicsFooter {
    public MyFooter(Context context) {
        super(context);
        init();
    }

    public MyFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        REFRESH_FOOTER_PULLUP = "load up more";
        REFRESH_FOOTER_RELEASE = "release immediately load";
        REFRESH_FOOTER_LOADING = "loading...";
        REFRESH_FOOTER_REFRESHING = "refreshing...";
        REFRESH_FOOTER_FINISH = "loading completed";
        REFRESH_FOOTER_FAILED = "loading failed";
        REFRESH_FOOTER_ALLLOADED = "all loaded completed";
    }
}
