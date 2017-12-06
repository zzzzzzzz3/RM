package com.quseit.payapp.bussiness.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.quseit.pay.PrintUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.adapter.MainFragmentAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.bean.response.MerchantBean;
import com.quseit.payapp.bussiness.membership.MembershipActivity;
import com.quseit.payapp.bussiness.pay.PaymentActivity;
import com.quseit.payapp.bussiness.setting.SettingActivity;
import com.quseit.payapp.bussiness.support.SupportActivity;
import com.quseit.payapp.bussiness.transations.TransationsActivity;
import com.quseit.payapp.bussiness.voucher.VoucherActivity;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.simple.eventbus.Subscriber;

import butterknife.BindView;

/**
 * 文 件 名: MainActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 12:34
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MainActivity extends BaseActivity implements MainContract.MainView{

    @BindView(R.id.vp_main)
    ViewPager mViewPager;
    @BindView(R.id.dots_layout)
    LinearLayout mDotsLayout;
    @BindView(R.id.avatar_img)
    ImageView mAvatarImg;
    @BindView(R.id.merchant_tv)
    TextView merchantTv;
    //ViewPager页面数
    private static final int len = 2;

    private RMProgressDialog mRMProgressDialog;
    private MainContract.MainPresenter mMainPresenter;

    @Override
    public int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        setUpViewpager();
        setupDotLayout();
        mRMProgressDialog = new RMProgressDialog(this);
    }

    private void setUpViewpager() {
        MainFragmentAdapter mAdapter = new MainFragmentAdapter(getSupportFragmentManager(),len);
        mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < len; i++) {
                    ImageView image = (ImageView) mDotsLayout.getChildAt(i);
                    if (position == i) {
                        image.setSelected(true);
                    } else {
                        image.setSelected(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupDotLayout() {
        for (int i = 0; i < len; i++) {
            ImageView image = newDotImage();
            if (i == 0) {
                image.setSelected(true);
            }
            mDotsLayout.addView(image);
        }
    }

    private ImageView newDotImage() {
        ImageView image = new ImageView(this);
        int size = UIUtil.dp2Px(this,10);
        int margin = UIUtil.dp2Px(this,5);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(size, size);
        lp.setMargins(margin,margin, margin, margin);
        image.setLayoutParams(lp);
        image.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.dot_selected_selector));
        return image;
    }

    @Override
    public void initData() {
        mMainPresenter = new MainPresenterImpl(this);
        MerchantBean bean = new MerchantBean();
        bean.setAvatar(PreferenceUtil.getInstance().getStr(GlobalBean.AVATAR));
        bean.setMerchant(PreferenceUtil.getInstance().getStr(GlobalBean.MERCHANT));
        if (!bean.getAvatar().isEmpty()&&!bean.getMerchant().isEmpty()){
            setData(bean);
        }else {
            // TODO: 2017/12/5 获取商户信息
            mMainPresenter.getMerchantInfo();
        }
    }

    @Override
    public String getToolbarTitle() {
        return null;
    }

    @Subscriber
    public void onItemClick(ItemBean itemBean) {
        switch (itemBean.itemName) {
            case GlobalBean.PAYMENT:
                startActivity(new Intent(this, PaymentActivity.class));
                break;
            case GlobalBean.VOUCHER:
                startActivity(new Intent(this, VoucherActivity.class));
                break;
            case GlobalBean.MEMBERSHIP:
                startActivity(new Intent(this, MembershipActivity.class));
                break;
            case GlobalBean.TRANSATIONS:
                startActivity(new Intent(this, TransationsActivity.class));
                break;
            case GlobalBean.APP_STORE:

                break;
            case GlobalBean.SETTING:
                startActivity(new Intent(this, SettingActivity.class));
                break;
            case GlobalBean.ORDERS:

                break;
            case GlobalBean.SUPPORT:
                startActivity(new Intent(this, SupportActivity.class));
                break;
            case GlobalBean.NOTIFICATION:

                break;

        }
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
    }

    @Override
    public void showMessage(String message) {
        toast(message);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public void setUpToken() {
        settingToken();
    }

    @Override
    public void setData(MerchantBean bean) {
        Glide.with(this).asBitmap().load(bean.getAvatar()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                mAvatarImg.setImageBitmap(resource);
            }
        });
        merchantTv.setText(bean.getMerchant());
    }
}
