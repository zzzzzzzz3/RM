package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.quseit.payapp.R;

/**
 * 文 件 名: RMEditDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/12 11:46
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RMEditDialog extends Dialog {

    private TextView titleTv;
    private EditText edit;
    private Button positiveBtn;


    public RMEditDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_rm_edit);
        initView();
    }

    private void initView() {
        titleTv = findViewById(R.id.dialog_title);
        edit = findViewById(R.id.dialog_edit);
        positiveBtn = findViewById(R.id.btn_ok);
        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public RMEditDialog setTitle(String title){
        titleTv.setText(title);
        return this;
    }

    public RMEditDialog setEditHint(String hint){
        edit.setHint(hint);
        return this;
    }

    public RMEditDialog setPositveBtn(final OnPositiveClickListener listener){
        positiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    dismiss();
                    listener.onClick(edit.getText().toString());
                }
            }
        });
        return this;
    }

    @Override
    public void show() {
        super.show();
        edit.requestFocus();
        edit.post(new Runnable() {
            @Override
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager)getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.toggleSoftInput(0, InputMethodManager.SHOW_FORCED);
            }
        });
    }

    public interface OnPositiveClickListener{
        void onClick(String editStr);
    }
}
