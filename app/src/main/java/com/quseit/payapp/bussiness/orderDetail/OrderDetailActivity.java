package com.quseit.payapp.bussiness.orderDetail;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GoodsAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.base.GoodBean;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.PayMethodBean;
import com.quseit.payapp.bean.response.TransationBean;
import com.quseit.payapp.bean.response.UserBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.util.UIUtil;
import com.quseit.payapp.widget.IconText;
import com.quseit.payapp.widget.RefundDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: OrderDetailActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 15:12
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class OrderDetailActivity extends BaseActivity implements OrderDetailContract.OrderDetailView{

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
    private TransationBean mTransationBean;

    private OrderDetailContract.OrderDetailPresenter mOrderDetailPresenter;
    private RefundDialog mRefundDialog;

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

        ViewCompat.setElevation(shadowLayout, UIUtil.dp2Px(this,10));

        mRefundDialog = new RefundDialog(this);

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
        mTransationBean = (TransationBean) getIntent().getSerializableExtra(GlobalBean.TRANSATION_BEAN);
        if (!mTransationBean.getStatus().equals("REFUNDED")){
            setRightText("Refund", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    refund();
                }
            });
        }
        String orderId = mTransationBean.getTransactionId();
        if (orderId.isEmpty()){
            orderNoTv.setText("-");
        }else {
            orderNoTv.setText(orderId);
        }
        String[] date = mTransationBean.getCreatedAt().replace("-","/").split("T|\\.");
        orderDateTv.setText(date[0]);
        orderTimeTv.setText(date[1]);
        if (mTransationBean.getString()!=null&&mTransationBean.getString().get(0)!=null){
            remarkTv.setText(mTransationBean.getString().get(0));
        }else {
            remarkTv.setText("-");
        }
        statusTv.setText(mTransationBean.getStatus());
        String amount = String.format("%.2f", (float) mTransationBean.getAmount() / 100);
        orderAmountTv.setText("MYR " + amount);
        switch (mTransationBean.getPaymentMethod()) {
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

    @Override
    public String getToolbarTitle() {
        return "Transation";
    }

    private void refund() {
        mOrderDetailPresenter.getUsers();
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

    @Override
    public void setUpToken() {

    }

    @Override
    public void showRefundDialog(final List<UserBean> list) {
        if (list.size()>0){
            if (mRefundDialog.getUserBean() == null){
                mRefundDialog.addUsers(list);
            }
            mRefundDialog.setRefundOnclick(new RefundDialog.OnRefundClick() {
                @Override
                public void onClick() {
                    toast("pass:"+mRefundDialog.getPassword()+"\nreson:"+mRefundDialog.getReson()+"\nuser:"+mRefundDialog.getUserBean().getLastName());
                }
            });
            mRefundDialog.show();
        }else {

        }
    }

    @Override
    public void showDialog(String msg, boolean success) {
        if (success){
            DialogManager.successDialog(this,msg,null);
        }else {
            DialogManager.failDialog(this,msg);
        }
    }
}
