package com.quseit.payapp.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.base.BaseAdapter;
import com.quseit.payapp.bean.ItemBean;
import com.quseit.payapp.widget.IconText;
import com.quseit.payapp.widget.RoundDrawable;

import org.simple.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: GridAdapter
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 22:44
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class GridAdapter extends BaseAdapter<ItemBean,GridAdapter.MainViewHolder> {

    private boolean hasTop;

    public GridAdapter(Context context, List<ItemBean> data) {
        super(context, data);
    }

    public void setHasTop(boolean hasTop){
        this.hasTop = hasTop;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0&&hasTop){
            return 1;
        }
        return 0;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
            GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
            layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    if (type == 1){
                        return 2;
                    }
                    return 1;
                }
            });
        }
    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_main,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final ItemBean itemBean = mData.get(position);
        holder.itemView.setBackgroundDrawable(new RoundDrawable(mContext.getResources(),null,ContextCompat.getColor(mContext,itemBean.colorSrc)));
        holder.itemIcon.setTitle(itemBean.iconFont);
        holder.itemNameTv.setText(itemBean.itemName);

        holder.clickView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(itemBean);
            }
        });
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.item_icon)
        IconText itemIcon;
        @BindView(R.id.item_name_tv)
        TextView itemNameTv;
        @BindView(R.id.click_view)
        FrameLayout clickView;

        public MainViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
