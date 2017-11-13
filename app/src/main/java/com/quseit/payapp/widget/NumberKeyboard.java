package com.quseit.payapp.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.util.UIUtil;

/**
 * 文 件 名: NumberKeyboard
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 14:38
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class NumberKeyboard extends RecyclerView {

    int[] nums = {1,2,3,4,5,6,7,8,9,-1,0,-2};
    private Context mContext;
    private OnKeyClickListener mListener;
    private String endKeyName;

    public NumberKeyboard(Context context) {
        this(context,null);
    }

    public NumberKeyboard(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NumberKeyboard(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    private void init() {
        setLayoutManager(new GridLayoutManager(mContext,3));
        setAdapter(new KeyboardAdapter());
        setOverScrollMode(OVER_SCROLL_NEVER);
        int ten = UIUtil.dp2Px(mContext,10);
        setPadding(ten*2,ten,ten*2,0);
        setMinimumHeight(ten*30);
    }

    public void setEndKeyName(String endKeyName) {
        this.endKeyName = endKeyName;
    }

    public void setOnKeyClickListener(OnKeyClickListener listener){
        mListener = listener;
    }

    public interface OnKeyClickListener{
        void onNumberClick(int number);
        void onDeleteClick();
        void onEndKeyClick();
    }

    public class KeyboardAdapter extends RecyclerView.Adapter{


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.rv_item_number_keyboard,parent,false);
            return new ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            final int num = nums[position];
            TextView numTv = (TextView) holder.itemView.findViewById(R.id.num_tv);
            ImageView img = (ImageView) holder.itemView.findViewById(R.id.fun_img);
           switch (num){
               case -1:
                    numTv.setVisibility(GONE);
                    img.setVisibility(VISIBLE);
                   holder.itemView.setOnClickListener(new OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if (mListener!=null){
                               mListener.onDeleteClick();
                           }
                       }
                   });
                   break;
               case -2:
                   numTv.setVisibility(VISIBLE);
                   numTv.setText(endKeyName==null?"Clear":endKeyName);
                   numTv.setTextColor(endKeyName==null?Color.GREEN:Color.BLUE);
                   img.setVisibility(GONE);
                   holder.itemView.setOnClickListener(new OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if (mListener!=null){
                               mListener.onEndKeyClick();
                           }
                       }
                   });
                   break;
               default:
                   numTv.setVisibility(VISIBLE);
                   numTv.setText(""+num);
                   numTv.setTextColor(Color.BLACK);
                   img.setVisibility(GONE);
                   holder.itemView.setOnClickListener(new OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           if (mListener!=null){
                               mListener.onNumberClick(num);
                           }
                       }
                   });
                   break;
           }
        }

        @Override
        public int getItemCount() {
            return nums.length;
        }
    }
}
