package com.quseit.payapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.base.GoodBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: GoodsAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 15:41
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class GoodsAdapter extends BaseAdapter<GoodBean,GoodsAdapter.GoodViewHolder> {

    public GoodsAdapter(Context context, List<GoodBean> data) {
        super(context, data);
    }

    @Override
    public GoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_goods,parent,false);
        return new GoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GoodViewHolder holder, int position) {
        GoodBean bean = mData.get(position);
        holder.nameTv.setText(bean.getName());
        holder.amountTv.setText("x"+bean.getAmount());
    }

    public class GoodViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.goods_name_tv)
        TextView nameTv;
        @BindView(R.id.goods_amount_tv)
        TextView amountTv;

        public GoodViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
