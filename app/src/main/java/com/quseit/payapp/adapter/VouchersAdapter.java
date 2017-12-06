package com.quseit.payapp.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import java.util.Currency;
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

public class VouchersAdapter extends BaseAdapter<VoucherBean, VouchersAdapter.VoucherViewHolder> {

    private VoucherBean selected;

    public VoucherBean getSelected() {
        return selected;
    }

    public void reset() {
        selected = null;
    }

    public VouchersAdapter(Context context, List<VoucherBean> data) {
        super(context, data);
    }

    @Override
    public VoucherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_voucher, parent, false);
        ViewCompat.setElevation(view,UIUtil.dp2Px(mContext,6));
        return new VoucherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VoucherViewHolder holder, final int position) {
        final VoucherBean bean = mData.get(position);
        holder.voucherName.setText(bean.getType() + " VOUCHER");
        holder.voucherType.setText(bean.getDiscountRate() + "%");
        if (bean == selected) {
            //holder.mRelativeLayout.setSelected(true);
            holder.plusIcon.setVisibility(View.VISIBLE);
            if (bean.getCount() > 0) {
                holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_amount_bg));
                holder.minusIcon.setVisibility(View.VISIBLE);
                holder.voucherCount.setVisibility(View.VISIBLE);
                holder.voucherCount.setText(bean.getCount() + "");
            } else {
                holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_bg));
                holder.minusIcon.setVisibility(View.GONE);
                holder.voucherCount.setVisibility(View.GONE);
            }
        } else {
            holder.minusIcon.setVisibility(View.GONE);
            holder.plusIcon.setVisibility(View.GONE);
            holder.voucherCount.setVisibility(View.GONE);
            holder.mRelativeLayout.setBackground(UIUtil.getInstance().getDrawable(R.mipmap.voucher_unseleceted));
            //holder.mRelativeLayout.setSelected(false);
        }

        holder.mRelativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (selected == bean) {
                    bean.setCount(0);
                    selected = null;
                } else {
                    if (selected != null)
                        selected.setCount(0);
                    selected = bean;
                }
                notifyDataSetChanged();
            }
        });

        holder.voucherRemark.setText(bean.getBalanceQuantity() + " Available");

        holder.plusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = bean.getCount();
                if (count < bean.getBalanceQuantity()) {
                    count++;
                }
                bean.setCount(count);
                notifyDataSetChanged();
            }
        });

        holder.minusIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = bean.getCount();
                if (count > 0) {
                    count--;
                }
                bean.setCount(count);
                notifyDataSetChanged();
            }
        });
    }

    class VoucherViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.voucher_name_tv)
        TextView voucherName;
        @BindView(R.id.voucher_type_tv)
        TextView voucherType;
        @BindView(R.id.voucher_remark_tv)
        TextView voucherRemark;
        @BindView(R.id.voucher_count_tv)
        TextView voucherCount;
        @BindView(R.id.plus_icon)
        View plusIcon;
        @BindView(R.id.minus_icon)
        View minusIcon;
        @BindView(R.id.item_bg)
        RelativeLayout mRelativeLayout;

        VoucherViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
