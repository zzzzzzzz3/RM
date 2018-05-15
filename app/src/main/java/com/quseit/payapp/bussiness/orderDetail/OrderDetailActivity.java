package com.quseit.payapp.bussiness.orderDetail;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.quseit.pay.PayInfoBean;
import com.quseit.pay.PayInfoBeanV3;
import com.quseit.pay.PrintUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GoodsAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.response.GoodBean;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.PayMethodBean;
import com.quseit.payapp.bean.response.TransationBean;
import com.quseit.payapp.bean.response.UserBean;
import com.quseit.payapp.bean.response.pay_v3.Transaction;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.PreferenceUtil;
import com.quseit.payapp.util.TimeConverterUtil;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.quseit.payapp.widget.RefundDialog;

import org.simple.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: OrderDetailActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 15:12
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.OrderDetailView {

    @BindView(R.id.order_no_tv)
    TextView orderNoTv;
    @BindView(R.id.order_date_tv)
    TextView orderDateTv;
    @BindView(R.id.order_time_tv)
    TextView orderTimeTv;
    @BindView(R.id.order_amount_tv)
    TextView orderAmountTv;
    @BindView(R.id.order_type_icon)
    ImageView orderTypeIcon;
    @BindView(R.id.order_remark_tv)
    TextView remarkTv;
    @BindView(R.id.order_detail_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.order_status_id)
    TextView statusTv;
    @BindView(R.id.shadow_layout)
    LinearLayout shadowLayout;
    private GoodsAdapter mGoodsAdapter;
    private List<GoodBean> mGoodBeans = new ArrayList<>();
    private Transaction mTransationBean;

    private OrderDetailContract.OrderDetailPresenter mOrderDetailPresenter;
    private RefundDialog mRefundDialog;
    private RMProgressDialog mRMProgressDialog;
    private PrintUtil mPrintUtil;
    private PayInfoBeanV3 mPayInfo;

    @Override
    public int getRootView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGoodBeans.addAll(creatList());
        mGoodsAdapter = new GoodsAdapter(this, mGoodBeans);
        mRecyclerView.setAdapter(mGoodsAdapter);

        ViewCompat.setElevation(shadowLayout, UIUtil.dp2Px(this, 10));

        mRefundDialog = new RefundDialog(this);
        mRMProgressDialog = new RMProgressDialog(this);

    }

    private List<GoodBean> creatList() {
        List<GoodBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            GoodBean bean = new GoodBean("Ice Cream", 6 - i);
            list.add(bean);
        }

        return list;
    }

    @Override
    public void initData() {
        mTransationBean = (Transaction) getIntent().getSerializableExtra(GlobalBean.TRANSATION_BEAN);
        initReceipt();
        if (!mTransationBean.getStatus().equals("REFUNDED")) {
            setRightText("Refund", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refund();
                }
            });
        }
        String orderId = mTransationBean.getTransactionId();
        if (orderId.isEmpty()) {
            orderNoTv.setText("-");
        } else {
            orderNoTv.setText(orderId);
        }
        try {
            String strDate = mTransationBean.getCreatedAt();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date1 = df.parse(strDate);
            strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
            String[] date = strDate.split(" ");
            orderDateTv.setText(date[0]);
            orderTimeTv.setText(date[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (mTransationBean.getOrder().getAdditionalData() != null) {
            remarkTv.setText(mTransationBean.getOrder().getAdditionalData());
        } else {
            remarkTv.setText("-");
        }
        statusTv.setText(mTransationBean.getStatus());
        String amount = String.format("%.2f", (float) mTransationBean.getOrder().getAmount() / 100);
        orderAmountTv.setText("MYR " + amount);
        switch (mTransationBean.getMethod()) {
            case PayMethodBean.ALIPAY:
                orderTypeIcon.setImageResource(R.mipmap.alipay_icon);
                break;
            case PayMethodBean.WECHATPAY:
                orderTypeIcon.setImageResource(R.mipmap.wechat_pay_icon);
                break;
            default:
                orderTypeIcon
                        .setImageResource(R.mipmap.voucher_pay_icon);
                break;
        }

        mOrderDetailPresenter = new OrderDetailPresenterImpl(this);
    }

    private void initReceipt() {
        mPayInfo = mTransationBean.getPrintInfo();
    }

    @Override
    public String getToolbarTitle() {
        return "Transation";
    }

    private void refund() {
        mOrderDetailPresenter.getUsers();
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
    public void showRefundDialog(final List<UserBean> list) {
        if (list.size() > 0) {
            if (mRefundDialog.getUserBean() == null) {
                mRefundDialog.addUsers(list);
            }
            mRefundDialog.setRefundOnclick(new RefundDialog.OnRefundClick() {
                @Override
                public void onClick() {
                    String pin = mRefundDialog.getPassword();
                    String reason = mRefundDialog.getReson();
                    String orderId = mTransationBean.getOrder().getId();
                    String key = mRefundDialog.getUserBean().getKey();
                    mOrderDetailPresenter.refund(pin, key, orderId, reason);
                }
            });
            mRefundDialog.show();
        } else {
            toast("no permission");
        }
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success) {
            DialogManager.successDialog(this, msg, new RMDialog.OnPositiveClickListener() {
                @Override
                public void onPositiveClick() {
                    EventBus.getDefault().post(GlobalBean.REFRESH);
                    finish();
                }
            });
        } else {
            DialogManager.failDialog(this, msg);
        }
    }

    @OnClick(R.id.btn_print)
    public void printPayInfo() {
        if (mPrintUtil == null) {
            mPrintUtil = new PrintUtil();
            mPrintUtil.deviceLogin(this);
        }
        mPrintUtil.printPayInfoV3(this, mPayInfo);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mOrderDetailPresenter.onDestroy();
        if (mPrintUtil != null) {
            mPrintUtil.logout();
        }
    }
}
