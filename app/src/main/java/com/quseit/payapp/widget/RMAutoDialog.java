package com.quseit.payapp.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Icon;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.quseit.payapp.R;
import com.quseit.payapp.bean.GlobalBean;

import java.lang.reflect.Type;

/**
 * 文 件 名: RMAutoDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/11 16:10
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RMAutoDialog extends Dialog {

    private IconText icon;
    private TextView msgTv;
    public enum TYPE{
        SUCCESS,FAILED
    }
    public static final int SHORT_DURATION = 1500;
    public static final int LONG_DURATION = 2000;
    private int mDuration = SHORT_DURATION;

    public RMAutoDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.toast_rm);
        initView();
    }

    private void initView() {
        icon = findViewById(R.id.toast_icon);
        msgTv = findViewById(R.id.toast_text);
    }

    public RMAutoDialog setDuration(int duration){
        mDuration = duration;
        return this;
    }

    public RMAutoDialog setType(TYPE type){
        if (type == TYPE.FAILED){
            icon.setText(getContext().getString(R.string.fail_font));
            icon.setTextColor(ContextCompat.getColor(getContext(),R.color.red));
        }else {
            icon.setText(getContext().getString(R.string.success_font));
            icon.setTextColor(ContextCompat.getColor(getContext(),R.color.green));
        }
        return this;
    }

    public RMAutoDialog setMessage(String msg){
        msgTv.setText(msg);
        return this;
    }

    @Override
    public void show() {
        super.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        },mDuration);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

        }
    };
}
