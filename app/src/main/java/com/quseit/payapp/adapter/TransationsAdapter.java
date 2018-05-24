package com.quseit.payapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.bean.PayMethodBean;
import com.quseit.payapp.bean.response.pay_v3.PayResponseV3;
import com.quseit.payapp.bean.response.pay_v3.Transaction;
import com.quseit.payapp.util.UIUtil;

import org.simple.eventbus.EventBus;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: TransationsAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/13 14:33
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class TransationsAdapter extends BaseAdapter<Transaction, TransationsAdapter.TransationViewHolder> {


    public TransationsAdapter(Context context, List<Transaction> data) {
        super(context, data);
    }

    @Override
    public TransationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_transation, parent, false);
        ViewCompat.setElevation(view, UIUtil.dp2Px(mContext, 8));
        return new TransationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransationViewHolder holder, int position) {
        final Transaction bean = mData.get(position);

        String orderId = bean.getTransactionId();
        if (orderId.isEmpty()) {
            holder.orderNoTv.setText("-");
        } else {
            holder.orderNoTv.setText(orderId);
        }
        if (bean.getOrder().getAdditionalData()!=null) {
            holder.remarkTv.setText(bean.getOrder().getAdditionalData());
        } else {
            holder.remarkTv.setText("-");
        }
        try {
            String strDate = bean.getCreatedAt();
            @SuppressLint("SimpleDateFormat")
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            Date date1= df.parse(strDate);
            strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date1);
            String[] date = strDate.split(" ");
            holder.timeTv.setText(date[1]);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String iconFont = bean.getMethod();
        switch (iconFont) {
            case PayMethodBean.ALIPAY:
                holder.icon.setImageResource(R.mipmap.alipay_icon);
                break;
            case PayMethodBean.WECHATPAY:
                holder.icon.setImageResource(R.mipmap.wechat_pay_icon);
                break;
            default:
                holder.icon.setImageResource(R.mipmap.voucher_pay_icon);
                break;
        }
        switch (bean.getStatus()) {
            case "SUCCESS":
                holder.status.setText("[SUCCESS]");
                holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.purple_color));
                break;
            case "FAILED":
                holder.status.setText("[FAILED]");
                holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.purple_color));
                break;
            case "FULL_REFUNDED":
                holder.status.setText("[FULL_REFUNDED]");
                holder.status.setTextColor(ContextCompat.getColor(mContext, R.color.purple_color));
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(bean);
            }
        });
    }

    public class TransationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.transation_order_no_tv)
        TextView orderNoTv;
        @BindView(R.id.transation_remark_tv)
        TextView remarkTv;
        @BindView(R.id.transation_time_tv)
        TextView timeTv;
        @BindView(R.id.transation_icon)
        ImageView icon;
        @BindView(R.id.transation_order_status_tv)
        TextView status;

        public TransationViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
