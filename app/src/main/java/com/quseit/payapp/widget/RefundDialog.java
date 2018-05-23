package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.CardPagerAdapter;
import com.quseit.payapp.bean.response.refund_users.UserBean;

import java.util.List;

/**
 * 文 件 名: RefundDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/12/7 10:51
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RefundDialog extends Dialog {


    private ViewPager mViewPager;
    private EditText password;
    private EditText reson;
    private Button refund;
    private UserBean mUserBean;
    private CardPagerAdapter mCardAdapter;

    public RefundDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_refund);
        initView();
    }

    private void initView() {
        mViewPager = findViewById(R.id.user_view_pager);
        password = findViewById(R.id.password_edit);
        reson = findViewById(R.id.reson_edit);
        refund = findViewById(R.id.refund_btn);
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mCardAdapter = new CardPagerAdapter();
    }

    public void addUsers(List<UserBean> beans) {
        mUserBean = beans.get(0);
        for (UserBean userBean : beans) {
            // TODO: 2017/12/7
            mCardAdapter.addCardItem(userBean);
        }
        mViewPager.setAdapter(mCardAdapter);
        mViewPager.setOffscreenPageLimit(3);
        mViewPager.setPageTransformer(false, new AlphaPageTransform());
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mUserBean = mCardAdapter.getUserAt(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public String getPassword() {
        return password.getText().toString();
    }

    public String getReson() {
        return reson.getText().toString();
    }

    public UserBean getUserBean() {
        return mUserBean;
    }

    public void setRefundOnclick(final OnRefundClick listener) {
        refund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener != null) {
                    listener.onClick();
                }
            }
        });
    }

    public interface OnRefundClick {
        void onClick();
    }

    class AlphaPageTransform implements ViewPager.PageTransformer {
        final float ALPHA_MAX = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            float alpha = ((position > 0.5 && position <= 1)||(position >= 1 && position <= 1.5))
                    ? 1
                    : 0.3f;
            Log.d("page", "alpha:" + alpha + " position:" + position);
            ViewCompat.setAlpha(page, Math.abs(alpha));
        }
    }

}
