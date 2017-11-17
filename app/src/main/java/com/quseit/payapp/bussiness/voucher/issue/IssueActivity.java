package com.quseit.payapp.bussiness.voucher.issue;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.VouchersAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.response.VoucherBean;
import com.quseit.payapp.util.DialogManager;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMProgressDialog;
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

public class IssueActivity extends BaseActivity implements IssueContract.IssueView{

    @BindView(R.id.issue_voucher_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout mSmartRefreshLayout;
    private VouchersAdapter mVouchersAdapter;
    private List<VoucherBean> mBeanList = new ArrayList<>();

    private IssueContract.IssuePresenter mIssuePresenter;

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
        mSmartRefreshLayout.autoRefresh();
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
        mIssuePresenter = new IssuePresenterImpl(this);
        mIssuePresenter.getVouchers();
    }

    @Override
    public String getToolbarTitle() {
        return "Issue Voucher";
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
        if (mSmartRefreshLayout.isRefreshing()){
            mSmartRefreshLayout.finishRefresh();
        }
        if (mSmartRefreshLayout.isLoading()){
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
        if (success){
            DialogManager.successDialog(this, msg, new RMDialog.OnPositiveClickListener() {
                @Override
                public void onPositiveClick() {

                }
            });
        }else {
            DialogManager.failDialog(this,msg);
        }
    }

    @Override
    public void setDataToList(List<VoucherBean> data) {
        mBeanList.addAll(data);
        mVouchersAdapter.notifyDataSetChanged();
    }
}
