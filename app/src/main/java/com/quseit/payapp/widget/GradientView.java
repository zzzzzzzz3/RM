package com.quseit.payapp.widget;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.quseit.payapp.R;

/**
 * 文 件 名: GradientView
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 13:52
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class GradientView extends View {

    public GradientView(Context context) {
        this(context,null);
    }

    public GradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        int[] colors = {ContextCompat.getColor(context, R.color.black),ContextCompat.getColor(context, R.color.right_blue),ContextCompat.getColor(context, R.color.payment_bg_color),ContextCompat.getColor(context, R.color.black),};
        GradientDrawable drawable = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT,colors);
        setBackground(drawable);
    }
}
