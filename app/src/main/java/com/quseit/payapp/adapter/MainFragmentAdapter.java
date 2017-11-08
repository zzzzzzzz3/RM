package com.quseit.payapp.adapter;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.quseit.payapp.R;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.ui.main.PageFragment;

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

public class MainFragmentAdapter extends FragmentPagerAdapter{

    private int len;
    private List<ItemBean> firstPageItems;
    private List<ItemBean> secondPageItems;

    public MainFragmentAdapter(FragmentManager fm,int pageCount) {
        super(fm);
        len = pageCount;
        firstPageItems = getData(5);
        secondPageItems = getData(6);
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment fragment = new PageFragment();
        switch (position){
            case 0:
                fragment.setHasTop(true);
                fragment.setPageItems(firstPageItems);
                break;
            case 1:
                fragment.setPageItems(secondPageItems);
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return len;
    }

    private List<ItemBean> getData(int size) {
        List<ItemBean> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ItemBean bean = new ItemBean(Color.BLUE,"item"+i, R.mipmap.ic_launcher);
            list.add(bean);
        }
        return list;
    }
}
