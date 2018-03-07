package com.quseit.payapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.quseit.payapp.bean.request.PayRequestBean;
import com.quseit.payapp.bean.response.PayResponseBean;
import com.quseit.payapp.bussiness.pay.PayContract;
import com.quseit.payapp.bussiness.pay.PayModelImpl;
import com.quseit.payapp.util.DataStore;

import org.junit.Test;
import org.junit.runner.RunWith;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.quseit.payapp", appContext.getPackageName());
    }

    @Test
    public void checkPay() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();

        DataStore.getInstance().init(context);

        PayContract.PayModel model = new PayModelImpl();
        model.pay(new PayRequestBean(100, "", "123", null)).subscribe(new Observer<PayResponseBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(PayResponseBean responseBean) {
                assertTrue(responseBean.getTransactionId(),true);
            }

            @Override
            public void onError(Throwable e) {
                assertTrue(e.getMessage(),true);
            }

            @Override
            public void onComplete() {
                assertTrue("success!",true);
            }
        });


    }
}
