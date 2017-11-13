package com.quseit.payapp.bussiness.membership;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GridAdapter;
import com.quseit.payapp.base.BaseActivity;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.bussiness.membership.points.GivePontsActivity;
import com.quseit.payapp.bussiness.membership.signup.SignUpActivity;
import com.quseit.payapp.bussiness.voucher.issue.IssueActivity;
import com.quseit.payapp.bussiness.voucher.redeem.RedeemActivity;

import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: MembershipActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 12:11
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MembershipActivity extends BaseActivity {

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
        pageItems.add(new ItemBean(R.color.points_bg_color, GlobalBean.POINTS,R.mipmap.place_holder_icon_white));
        pageItems.add(new ItemBean(R.color.sign_up_bg_color,GlobalBean.SIGN_UP,R.mipmap.place_holder_icon_white));
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
            case GlobalBean.POINTS:{
                startActivity(new Intent(this, GivePontsActivity.class));
                break;
            }
            case GlobalBean.SIGN_UP:{
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            }
        }
    }
}
