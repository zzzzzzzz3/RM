package com.quseit.payapp.bussiness.test;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseActivity;

public class TestActivity extends BaseActivity implements TestContract.TestView {

    @Override
    public int getRootView() {
        return R.layout.activity_test;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(String message) {

    }

    @Override
    public void killMyself() {

    }
}
