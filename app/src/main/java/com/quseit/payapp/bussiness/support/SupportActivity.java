package com.quseit.payapp.bussiness.support;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;

import butterknife.BindView;

/**
 * 文 件 名: SupportActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/14 10:00
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SupportActivity extends BaseActivity {

    @Override
    public int getRootView() {
        return R.layout.activity_support;
    }

    @Override
    public void initView() {
        setRightIcon(R.mipmap.search_icon, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("search");
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Support";
    }
}
