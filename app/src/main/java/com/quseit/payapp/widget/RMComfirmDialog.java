package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
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

public class RMComfirmDialog extends Dialog {

    private IconText icon;
    private TextView textTv;
    private Button mPositiveButton;
    private Button mNegativeButton;

    public RMComfirmDialog(@NonNull Context context) {
        this(context, R.style.Dialog);
    }

    public RMComfirmDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_comfirm_rm);
        initView();
    }

    private void initView() {
        icon = findViewById(R.id.dialog_icon);
        textTv = findViewById(R.id.dialog_text);
        mPositiveButton = findViewById(R.id.btn_ok);
        mNegativeButton = findViewById(R.id.btn_cancel);
    }

    public RMComfirmDialog setIcon(String fontIcon) {
        icon.setText(fontIcon);
        return this;
    }

    public RMComfirmDialog setIconColor(int color) {
        icon.setTextColor(color);
        return this;
    }

    public RMComfirmDialog setText(String str) {
        textTv.setText(str);
        return this;
    }

    public RMComfirmDialog setTextColor(int color) {
        textTv.setTextColor(color);
        return this;
    }

    public RMComfirmDialog setPositionBtn(String text, final RMDialog.OnPositiveClickListener listener) {
        if (text != null && !text.equals("")) {
            mPositiveButton.setVisibility(View.VISIBLE);
            mPositiveButton.setText(text);
            mPositiveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onPositiveClick();
                    }
                }
            });

        }
        return this;
    }

    public RMComfirmDialog setNegativeBtn(String text, final RMDialog.OnComfirmClickListener listener) {
        if (text != null && !text.equals("")) {
            mNegativeButton.setVisibility(View.VISIBLE);
            mNegativeButton.setText(text);
            mNegativeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                    if (listener != null) {
                        listener.onComfirmClick();
                    }
                }
            });

        }
        return this;
    }

    public void show(boolean success) {
        if (success) {
            mPositiveButton.setVisibility(View.VISIBLE);
            mNegativeButton.setVisibility(View.GONE);
        } else {
            mNegativeButton.setVisibility(View.VISIBLE);
            mPositiveButton.setVisibility(View.GONE);
        }
        show();
    }
}
