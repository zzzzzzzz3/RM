package com.quseit.payapp.bussiness.main;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.quseit.payapp.R;
import com.quseit.payapp.adapter.GridAdapter;
import com.quseit.payapp.base.BaseFragment;
import com.quseit.payapp.bean.ItemBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 文 件 名: PageFragment
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 21:26
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PageFragment extends BaseFragment {

    @BindView(R.id.main_rv)
    RecyclerView mRecyclerView;
    private GridAdapter mGridAdapter;
    private List<ItemBean> pageItems;
    private boolean mHasTop;

    public void setHasTop(boolean hasTop) {
        mHasTop = hasTop;
    }

    public void setPageItems(List<ItemBean> pageItems) {
        this.pageItems = pageItems;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_main_first;
    }

    @Override
    public void initView() {
        mGridAdapter = new GridAdapter(mContext, pageItems);
        mGridAdapter.setHasTop(mHasTop);
        mRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mRecyclerView.setAdapter(mGridAdapter);
    }

    @Override
    public void initData() {

    }

}
