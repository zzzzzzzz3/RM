package com.quseit.payapp.ui;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/9/12.
 */
public abstract class BaseFragment extends Fragment
{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        ViewDataBinding mBinding= DataBindingUtil.inflate(inflater,setViewId(),container,false);
        initView(mBinding);// 初始化控件
        initEvent();// 初始化点击事件
        init();// 初始化界面
        loadData();// 请求数据
        return mBinding.getRoot();
    }

    protected abstract void initView(ViewDataBinding binding);

    protected abstract void initEvent();

    protected abstract void init();

    protected abstract void loadData();

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setViewId();
}
