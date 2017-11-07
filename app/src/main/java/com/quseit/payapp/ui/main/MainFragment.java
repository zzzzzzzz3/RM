package com.quseit.payapp.ui.main;

import android.databinding.ViewDataBinding;
import android.os.Bundle;

import com.quseit.payapp.R;
import com.quseit.payapp.ui.BaseFragment;

/**
 * Created by quseitu on 2017/11/7.
 */

public class MainFragment extends BaseFragment{
    Bundle bundle;
    public MainFragment(){

    }

    public static MainFragment newInstance(boolean type) {
        MainFragment f = new MainFragment();
        Bundle bundle=new Bundle();
        bundle.putBoolean("MAIN",type);
        f.setArguments(bundle);
        return f;
    }

    @Override
    protected void initView(ViewDataBinding binding) {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    protected void init() {

    }

    @Override
    protected void loadData() {

    }

    @Override
    protected int setViewId() {
        bundle=getArguments();
        return bundle.getBoolean("MAIN",true)? R.layout.fragment_main_first:R.layout.fragment_mian_second;
    }
}
