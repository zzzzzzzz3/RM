package com.quseit.payapp.bussiness.orderDetail;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GoodsAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.base.GoodBean;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.widget.IconText;

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

public class OrderDetailActivity extends BaseActivity {

    @BindView(R.id.toolbar_right_tv)
    TextView refundTv;
    @BindView(R.id.order_no_tv)
    TextView orderNoTv;
    @BindView(R.id.order_date_tv)
    TextView orderDateTv;
    @BindView(R.id.order_time_tv)
    TextView orderTimeTv;
    @BindView(R.id.order_amount_tv)
    TextView orderAmountTv;
    @BindView(R.id.order_type_icon)
    IconText orderTypeIcon;
    @BindView(R.id.order_detail_rv)
    RecyclerView mRecyclerView;
    private GoodsAdapter mGoodsAdapter;
    private List<GoodBean> mGoodBeans = new ArrayList<>();

    @Override
    public int getRootView() {
        return R.layout.activity_order_detail;
    }

    @Override
    public void initView() {
        refundTv.setText("Refund");
        refundTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refund();
            }
        });

        orderTypeIcon.setText(GlobalBean.CASH_ICON);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGoodBeans.addAll(creatList());
        mGoodsAdapter = new GoodsAdapter(this,mGoodBeans);
        mRecyclerView.setAdapter(mGoodsAdapter);
    }

    private void refund() {
        toast("refund");
    }

    private List<GoodBean> creatList() {
        List<GoodBean> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            GoodBean bean = new GoodBean("Ice Cream"+i,6-i);
            list.add(bean);
        }

        return list;
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Transation";
    }
}
