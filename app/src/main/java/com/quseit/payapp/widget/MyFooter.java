package com.quseit.payapp.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.quseit.payapp.R;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;

/**
 * 文 件 名: MyFooter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/27 11:25
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
        REFRESH_FOOTER_PULLUP = getContext().getString(R.string.loadmore);
        REFRESH_FOOTER_RELEASE = getContext().getString(R.string.release_load);
        REFRESH_FOOTER_LOADING = getContext().getString(R.string.loading);
        REFRESH_FOOTER_REFRESHING = getContext().getString(R.string.refreshing);
        REFRESH_FOOTER_FINISH = getContext().getString(R.string.load_complete);
        REFRESH_FOOTER_FAILED = getContext().getString(R.string.load_fail);
        REFRESH_FOOTER_ALLLOADED = getContext().getString(R.string.load_all_complete);
    }
}
