package com.quseit.payapp;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.Http.Api;
import com.quseit.payapp.bussiness.pay.PaymentActivity;
import com.quseit.payapp.util.DataStore2;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 文 件 名: PayInstrumentedTest
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/3/7 11:05
 * 修改时间：
 * 修改备注：
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PayInstrumentedTest {

    private Context context = InstrumentationRegistry.getTargetContext();

    @Rule
    public ActivityTestRule<PaymentActivity> mPaymentActivityActivityTestRule = new ActivityTestRule<PaymentActivity>(PaymentActivity.class){

        @Override
        protected void beforeActivityLaunched() {
            super.beforeActivityLaunched();
            DataStore2.getInstance().init(context);
            Map<String,String> headers = new HashMap<>();
            headers.put("X-Rm-Platform","application/terminal");
            RetrofitManager.getInstance()
                    .setTimeOut(15)
                    .openDebug(BuildConfig.DEBUG)
                    .supportSSL()
                    .addHeaders(headers)
                    .init(Api.BASE_URL);
        }
    };

    @Before
    public void setUp() throws Exception {
        IdlingRegistry.getInstance().register(
                mPaymentActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @After
    public void tearDown() throws Exception {
        IdlingRegistry.getInstance().unregister(
                mPaymentActivityActivityTestRule.getActivity().getCountingIdlingResource());
    }


    @Test
    public void inputAmount() throws Exception{
        //输入金额
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(2,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(4,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(6,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(8,click()));
        onView(withId(R.id.payment_number_tv)).check(matches(withText("135.79")));
        //删除
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(9,click()));
        onView(withId(R.id.payment_number_tv)).check(matches(withText("13.57")));
        //清空
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(11,click()));
        onView(withId(R.id.payment_number_tv)).check(matches(withText("0.00")));

    }

    @Test
    public void pay_without_aithcode() throws Exception{
        //输入金额
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(0,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(10,click()));
        onView(withId(R.id.keyboard_number)).perform(RecyclerViewActions.actionOnItemAtPosition(10,click()));

        //点击支付
        onView(withId(R.id.cash_icon)).perform(click());
        //确认支付
        onView(withId(R.id.btn_ok)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_ok)).perform(click());

        //反馈
        onView(withText("Success")).check(matches(isDisplayed()));
        onView(withId(R.id.btn_ok)).perform(click());
    }

}
