package com.quseit.payapp.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 22:13
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    protected Context mContext;
    protected List<T> mData;

    public BaseAdapter(Context context, List<T> data) {
        mContext = context;
        mData = data;
    }

    public void setData(List<T> data){
        mData = data;
        notifyDataSetChanged();
    }

    public void loadMore(List<T> data){
        mData.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }
}
