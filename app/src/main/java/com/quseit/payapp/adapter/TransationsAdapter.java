package com.quseit.payapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.bean.PayMethodBean;
import com.quseit.payapp.bean.response.TransationBean;

import org.simple.eventbus.EventBus;

import java.util.List;

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

public class TransationsAdapter extends BaseAdapter<TransationBean,TransationsAdapter.TransationViewHolder> {


    public TransationsAdapter(Context context, List<TransationBean> data) {
        super(context, data);
    }

    @Override
    public TransationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_transation,parent,false);
        return new TransationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransationViewHolder holder, int position) {
        final TransationBean bean = mData.get(position);

        holder.orderNoTv.setText(bean.getOrderId());
        if (bean.getString()!=null&&bean.getString().get(0)!=null){
            holder.remarkTv.setText(bean.getString().get(0));
        }else {
            holder.remarkTv.setText("-");
        }
        String createTime = bean.getCreatedAt().split("T|\\.")[1];
        holder.timeTv.setText(createTime);
        String iconFont = bean.getPaymentMethod();
        switch (iconFont){
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
        switch (bean.getStatus()){
            case "SUCCESS":
                holder.status.setText("[SUCCESS]");
                holder.status.setTextColor(Color.GREEN);
                break;
            case "FAILED":
                holder.status.setText("[FAILED]");
                holder.status.setTextColor(Color.RED);
                break;
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(bean);
            }
        });
    }

    public class TransationViewHolder extends RecyclerView.ViewHolder{

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
            ButterKnife.bind(this,itemView);
        }
    }
}
