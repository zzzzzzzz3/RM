package com.quseit.payapp.bussiness.voucher.issue;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.pay.PrintUtil;
import com.quseit.payapp.R;
import com.quseit.payapp.adapter.VouchersAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.response.VoucherBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

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

public class IssueActivity extends BaseActivity implements IssueContract.IssueView {

    @BindView(R.id.issue_voucher_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    @BindView(R.id.empty_view)
    ImageView emptyView;
    private VouchersAdapter mVouchersAdapter;
    private List<VoucherBean> mBeanList = new ArrayList<>();

    private IssueContract.IssuePresenter mIssuePresenter;
    private RMProgressDialog mRMProgressDialog;

    @Override
    public int getRootView() {
        return R.layout.activity_issue_voucher;
    }

    @Override
    public void initView() {
        mVouchersAdapter = new VouchersAdapter(this, mBeanList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mVouchersAdapter);
        setRightText("Print", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherBean bean = mVouchersAdapter.getSelected();
                if (bean == null || bean.getCount() == 0) {
                    toast("please select a voucher");
                } else {
                    mIssuePresenter.printQRcode(IssueActivity.this, bean.getId(), bean.getCreatedAt(), bean.getCount());
                }
            }
        });
        mSmartRefreshLayout.autoRefresh();
        mSmartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mIssuePresenter.getVouchers();
            }
        }).setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                mIssuePresenter.loadMore();
            }
        });

        mRMProgressDialog = new RMProgressDialog(this);
    }

    @Override
    public void initData() {
        mIssuePresenter = new IssuePresenterImpl(this);
    }

    @Override
    public String getToolbarTitle() {
        return "Issue Voucher";
    }

    @Override
    public void showLoading() {
        mRMProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mRMProgressDialog.dismiss();
        if (mSmartRefreshLayout.isRefreshing()) {
            mSmartRefreshLayout.finishRefresh();
        }
        if (mSmartRefreshLayout.isLoading()) {
            mSmartRefreshLayout.finishLoadmore();
        }
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
    public void showDialog(String msg, boolean success) {
        if (success) {
            DialogManager.successDialog(this, msg, null);
        } else {
            DialogManager.failDialog(this, msg);
        }
    }

    @Override
    public void setDataToList(List<VoucherBean> data) {
        mVouchersAdapter.reset();
        if (data.size() > 0) {
            emptyView.setVisibility(View.GONE);
            mBeanList = data;
            mVouchersAdapter.setData(mBeanList);
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loadMore(List<VoucherBean> data) {
        mBeanList.addAll(data);
        mVouchersAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mIssuePresenter.onDestroy();
    }
}
