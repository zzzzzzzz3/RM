package com.quseit.payapp.bussiness.voucher.issue;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.VouchersAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.response.VoucherBean;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 文 件 名: IssueActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 10:51
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class IssueActivity extends BaseActivity {

    @BindView(R.id.issue_voucher_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private VouchersAdapter mVouchersAdapter;
    private List<VoucherBean> mBeanList = new ArrayList<>();

    @Override
    public int getRootView() {
        return R.layout.activity_issue_voucher;
    }

    @Override
    public void initView() {
        mBeanList.addAll(createList());
        mVouchersAdapter = new VouchersAdapter(this,mBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mVouchersAdapter);

        setRightText("Print", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toast("print");
            }
        });
    }

    private List<VoucherBean> createList() {
        List<VoucherBean> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            VoucherBean bean = new VoucherBean("Voucher","2000 aviliable","RM5",0);
            list.add(bean);
        }
        return list;

    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Issue Voucher";
    }
}
