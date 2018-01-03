package com.quseit.payapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.quseit.payapp.R;

import java.util.List;

/**
 * 文 件 名: SelectDialog
 * 创 建 人: ZhangRonghua
 * 创建日期: 2018/1/3 10:22
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class SelectDialog extends Dialog {

    TextView okTv;
    WheelView mWheelView;
    private String selected;

    public SelectDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        setContentView(R.layout.dialog_select);
        okTv = findViewById(R.id.ok_tv);
        mWheelView = findViewById(R.id.wheel_view);
        findViewById(R.id.cancel_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        mWheelView.setOffset(1);
        mWheelView.setOnWheelViewListener(new WheelView.OnWheelViewListener(){
            @Override
            public void onSelected(int selectedIndex, String item) {
                selected = item;
            }
        });
    }

    public void setItems(List<String> items){
        if (items == null){
            return;
        }
        mWheelView.setItems(items);
        selected = items.get(0);
    }

    public void setOkCallback(final OkCallback callback){
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callback !=null){
                    callback.onOk(selected);
                }
            }
        });
    }

    @Override
    public void show() {
        super.show();
        Window window = getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.BottomAnim);
    }

    public interface OkCallback{
        void onOk(String item);
    }

}
