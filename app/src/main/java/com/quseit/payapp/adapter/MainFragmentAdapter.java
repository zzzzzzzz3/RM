package com.quseit.payapp.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.quseit.payapp.R;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.bussiness.main.PageFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 文 件 名: MainFragmentAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/7 23:34
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class MainFragmentAdapter extends FragmentPagerAdapter {

    private int len;
    private List<ItemBean> firstPageItems;
    private List<ItemBean> secondPageItems;

    public MainFragmentAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        len = pageCount;
        firstPageItems = createFirstItems();
        secondPageItems = createSeccondItems();
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment fragment = new PageFragment();
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putSerializable(GlobalBean.ITEMS, (Serializable) firstPageItems);
                bundle.putBoolean(GlobalBean.HAS_TOP,true);
                break;
            case 1:
                bundle.putSerializable(GlobalBean.ITEMS, (Serializable) secondPageItems);
                break;
        }
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return len;
    }

    private List<ItemBean> createFirstItems() {
        List<ItemBean> list = new ArrayList<>();
        ItemBean bean1 = new ItemBean(R.color.payment_bg_color, GlobalBean.PAYMENT, R.mipmap.place_holder_icon_white);
        list.add(bean1);
        ItemBean bean2 = new ItemBean(R.color.voucher_bg_color, GlobalBean.VOUCHER, R.mipmap.place_holder_icon_white);
        list.add(bean2);
        ItemBean bean3 = new ItemBean(R.color.membership_bg_color, GlobalBean.MEMBERSHIP, R.mipmap.place_holder_icon_white);
        list.add(bean3);
        ItemBean bean4 = new ItemBean(R.color.history_bg_color, GlobalBean.TRANSATIONS, R.mipmap.place_holder_icon_white);
        list.add(bean4);
        ItemBean bean5 = new ItemBean(R.color.app_store_bg_color, GlobalBean.APP_STORE, R.mipmap.place_holder_icon_white);
        list.add(bean5);
        return list;
    }

    private List<ItemBean> createSeccondItems() {
        List<ItemBean> list = new ArrayList<>();
        ItemBean bean1 = new ItemBean(R.color.setting_bg_color, GlobalBean.SETTING, R.mipmap.place_holder_icon_white);
        list.add(bean1);
        ItemBean bean2 = new ItemBean(R.color.orders_bg_color, GlobalBean.ORDERS, R.mipmap.place_holder_icon_white);
        list.add(bean2);
        ItemBean bean3 = new ItemBean(R.color.support_bg_color, GlobalBean.SUPPORT, R.mipmap.place_holder_icon_white);
        list.add(bean3);
        ItemBean bean4 = new ItemBean(R.color.notification_bg_color, GlobalBean.NOTIFICATION, R.mipmap.place_holder_icon_white);
        list.add(bean4);
        list.add(bean4);
        list.add(bean4);
        return list;
    }

}
