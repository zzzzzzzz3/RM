package com.quseit.payapp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quseit.payapp.R;

import org.simple.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 20:52
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseFragment extends Fragment {

    protected Context mContext;
    private View mRootView = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getRootView(), container, false);
            if (mRootView != null) {
                ButterKnife.bind(this, mRootView);
                initView();
                initData();
            } else {
                mRootView = inflater.inflate(R.layout.layout_no_set_view, container, false);
            }
        }
        return mRootView;
    }

    public abstract int getRootView();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
