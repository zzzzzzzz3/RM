package com.quseit.payapp.bussiness.test;

import com.quseit.payapp.base.BasePresenter;

public class TestPresenterImpl extends BasePresenter implements TestContract.TestPresenter {

    private TestContract.TestView mTestView;
    private TestContract.TestModel mTestModel;

    public TestPresenterImpl(TestContract.TestView view) {
        mTestView = view;
        mTestModel = new TestModelImpl();
    }

    @Override
    public void onDestroy() {
        unDispose();
    }
}
