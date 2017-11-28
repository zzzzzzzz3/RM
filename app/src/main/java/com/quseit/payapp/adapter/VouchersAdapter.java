package com.quseit.payapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.bean.response.VoucherBean;
import com.quseit.payapp.util.UIUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.http.Body;

/**
 * 文 件 名: VouchersAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 11:23
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class VouchersAdapter extends BaseAdapter<VoucherBean,VouchersAdapter.VoucherViewHolder> {


    private List<VoucherBean> selectedList = new ArrayList<>();

    public List<VoucherBean> getSelectedList() {
        return selectedList;
    }

    public VouchersAdapter(Context context, List<VoucherBean> data) {
        super(context, data);
    }

    @Override
    public VoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_voucher,parent,false);
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VoucherViewHolder holder, final int position) {
        final VoucherBean bean = mData.get(position);
        holder.voucherName.setText(bean.getType()+" VOUCHER");
        holder.voucherType.setText(bean.getDiscountRate()+"%");
        if (bean.getAmount() >0){
            holder.voucherCount.setVisibility(View.VISIBLE);
            holder.minusIcon.setVisibility(View.VISIBLE);
            holder.voucherCount.setText(bean.getAmount()+"");
        }else {
            holder.voucherCount.setVisibility(View.GONE);
            holder.minusIcon.setVisibility(View.GONE);
            bean.setSelected(false);
        }

        if (bean.getAmount()>0){
            holder.mRelativeLayout.setEnabled(true);
            holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bean.setSelected(!bean.isSelected());
                    notifyDataSetChanged();
                }
            });
            if (bean.isSelected()){
                holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_selected_bg));
                holder.mRelativeLayout.setSelected(true);
            }else {
                holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_amount_bg));
                holder.mRelativeLayout.setSelected(false);
            }
        }else {
            holder.mRelativeLayout.setEnabled(false);
            holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_bg));
        }

        holder.voucherRemark.setText(bean.getBalanceQuantity()+" Available");

        holder.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherBean temp = mData.get(position);
                int count  = temp.getAmount()+1;
                temp.setAmount(count);
                notifyDataSetChanged();
            }
        });

        holder.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VoucherBean temp = mData.get(position);
                int count  = temp.getAmount()-1;
                temp.setAmount(count);
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
        @BindView(R.id.item_bg)
        RelativeLayout mRelativeLayout;

        public VoucherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
