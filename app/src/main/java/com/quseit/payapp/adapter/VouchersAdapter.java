package com.quseit.payapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.bean.response.VoucherBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: VouchersAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 11:23
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VouchersAdapter extends BaseAdapter<VoucherBean,VouchersAdapter.VoucherViewHolder> {


    public VouchersAdapter(Context context, List<VoucherBean> data) {
        super(context, data);
    }

    @Override
    public VoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_voucher,parent,false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VoucherViewHolder holder, final int position) {
        VoucherBean bean = mData.get(position);
        holder.voucherName.setText(bean.getVoucherName());
        holder.voucherType.setText(bean.getVoucherType());
        if (bean.getVoucherCount() >0){
            holder.voucherCount.setVisibility(View.VISIBLE);
            holder.minusIcon.setVisibility(View.VISIBLE);
            holder.voucherCount.setText(bean.getVoucherCount()+"");
        }else {
            holder.voucherCount.setVisibility(View.GONE);
            holder.minusIcon.setVisibility(View.GONE);
        }
        holder.voucherRemark.setText(bean.getVoucherRemark());

        holder.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherBean temp = mData.get(position);
                int count  = temp.getVoucherCount()+1;
                temp.setVoucherCount(count);
                notifyDataSetChanged();
            }
        });

        holder.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherBean temp = mData.get(position);
                int count  = temp.getVoucherCount()-1;
                temp.setVoucherCount(count);
                notifyDataSetChanged();
            }
        });
    }

    public class VoucherViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.voucher_name_tv)
        TextView voucherName;
        @BindView(R.id.voucher_type_tv)
        TextView voucherType;
        @BindView(R.id.voucher_remark_tv)
        TextView voucherRemark;
        @BindView(R.id.voucher_count_tv)
        TextView voucherCount;
        @BindView(R.id.plus_icon)
        ImageView plusIcon;
        @BindView(R.id.minus_icon)
        ImageView minusIcon;

        public VoucherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
