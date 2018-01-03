package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.quseit.payapp.R;

/**
 * 文 件 名: RMDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/11 14:16
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RMDialog extends Dialog {

    private IconText icon;
    private TextView textTv;
    private TextView subTextTv;
    private Button mPositiveButton;
    private Button mNegativeButton;
    private LinearLayout btnLayout;
    private Button mComfirmBtn;

    public RMDialog(@NonNull Context context) {
        this(context,R.style.Dialog);
    }

    public RMDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_rm);
        initView();
    }

    private void initView() {
        icon = (IconText) findViewById(R.id.dialog_icon);
        textTv = (TextView) findViewById(R.id.dialog_text);
        subTextTv = (TextView) findViewById(R.id.dialog_sub_text);
        mPositiveButton = (Button) findViewById(R.id.btn_ok);
        mNegativeButton = (Button) findViewById(R.id.btn_cancel);
        mComfirmBtn = findViewById(R.id.btn_comfirm);
        btnLayout = findViewById(R.id.btn_layout);

        mNegativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public RMDialog setIcon(String fontIcon){
        icon.setText(fontIcon);
        return this;
    }

    public RMDialog setIconColor(int color){
        icon.setTextColor(color);
        return this;
    }

    public RMDialog setText(String str){
        textTv.setText(str);
        return this;
    }

    public RMDialog setTextColor(int color){
        textTv.setTextColor(color);
        return this;
    }

    public RMDialog setSubText(String str){
        if (str!=null&&!str.equals("")){
            subTextTv.setVisibility(View.VISIBLE);
            subTextTv.setText(str);
        }
        return this;
    }

    public RMDialog setSubTextColor(int color){
        subTextTv.setTextColor(color);
        return this;
    }

    public RMDialog setPositionBtn(final OnPositiveClickListener listener){
        btnLayout.setVisibility(View.VISIBLE);
            mPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onPositiveClick();
                    }
                }
            });

        return this;
    }

    public RMDialog setComfirmBtn(final OnComfirmClickListener listener){
        mComfirmBtn.setVisibility(View.VISIBLE);
        mComfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (listener!=null){
                    listener.onComfirmClick();
                }
            }
        });
        return this;
    }

    public interface OnPositiveClickListener {
        void onPositiveClick();
    }

    public interface OnComfirmClickListener {
        void onComfirmClick();
    }
}
