package com.quseit.payapp.bussiness.voucher;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GridAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.bussiness.voucher.issue.IssueActivity;
import com.quseit.payapp.bussiness.voucher.redeem.RedeemActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: VoucherActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 10:16
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VoucherActivity extends BaseActivity{

    @BindView(R.id.rv)
    RecyclerView rv;
    private GridAdapter mGridAdapter;
    private List<ItemBean> pageItems = new ArrayList<>();


    @Override
    public int getRootView() {
        return R.layout.activity_voucher;
    }

    @Override
    public void initView() {
        pageItems.add(new ItemBean(R.color.redeep_bg_color, GlobalBean.REDEEM,GlobalBean.REDEEM_ICON));
        pageItems.add(new ItemBean(R.color.issue_bg_color,GlobalBean.ISSUE,GlobalBean.ISSUE_ICON));
        mGridAdapter = new GridAdapter(this, pageItems);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setAdapter(mGridAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public String getToolbarTitle() {
        return "Vocher";
    }


    @Subscriber
    public void onClick(ItemBean itemBean){
        switch (itemBean.itemName){
            case GlobalBean.REDEEM:{
                startActivity(new Intent(this, RedeemActivity.class));
                break;
            }
            case GlobalBean.ISSUE:{
                startActivity(new Intent(this, IssueActivity.class));
                break;
            }
        }
    }
}
