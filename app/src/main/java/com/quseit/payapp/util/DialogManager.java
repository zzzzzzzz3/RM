package com.quseit.payapp.util;

import android.content.Context;

import com.quseit.payapp.R;
import com.quseit.payapp.widget.IOSDialog;
import com.quseit.payapp.widget.RMAutoDialog;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMEditDialog;
import com.quseit.payapp.widget.RMProgressDialog;


/**
 * 文 件 名: DialogManager
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/25 17:59
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class DialogManager {

    /**
     * 确认对话框
     */
    public static IOSDialog iosDialog(Context context, String title, String msg, IOSDialog.OnPositiveClickListener listener) {
        IOSDialog dialog = new IOSDialog(context, R.style.Dialog);
        dialog.setTitle(title);
        dialog.setContent(msg);
        dialog.setPositiveButton("确认", listener);
        dialog.setNegativeButton("取消", new IOSDialog.OnNegativeClickListener() {

            @Override
            public void onNegativeClick() {

            }
        });
        dialog.show();
        return dialog;
    }

    public static IOSDialog iosDialog(Context context, String title, String msg) {
        IOSDialog dialog = new IOSDialog(context, R.style.Dialog);
        dialog.setTitle(title);
        dialog.setContent(msg);
        dialog.setPositiveButton("确认", new IOSDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {

            }
        });
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialog(Context context, String text, int resID, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog)
                .setText(text)
                .setIcon(resID)
                .setPositionBtn("OK", listener)
                .setNegativeBtn("Cancel", new RMDialog.OnNegativeClickListener() {
                    @Override
                    public void onNegativeClick() {

                    }
                });
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialog(Context context, String text, int resID) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog)
                .setText(text)
                .setIcon(resID)
                .setPositionBtn("OK", new RMDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick() {

                    }
                });
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogNoCancel(Context context, String text, int resID, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(resID);
        dialog.setPositionBtn("OK", listener);
        dialog.show();
        return dialog;
    }

    public static RMAutoDialog rmAutoDialog(Context context, String msg, RMAutoDialog.TYPE type) {
        RMAutoDialog dialog = new RMAutoDialog(context)
                .setMessage(msg)
                .setType(type);
        dialog.show();
        return dialog;
    }

    public static RMProgressDialog rmProgressDialog(Context context, String msg) {
        RMProgressDialog dialog = new RMProgressDialog(context).setMsg(msg);
        dialog.show();
        return dialog;
    }

    public static RMEditDialog rmEditDialog(Context context,String title,String hint,RMEditDialog.OnPositiveClickListener listener){
        RMEditDialog dialog = new RMEditDialog(context)
                .setEditHint(hint)
                .setTitle(title)
                .setPositveBtn(listener);
        dialog.show();
        return dialog;
    }

}