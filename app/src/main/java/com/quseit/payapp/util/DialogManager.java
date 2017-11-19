package com.quseit.payapp.util;

import android.content.Context;

import com.quseit.payapp.R;
import com.quseit.payapp.bean.GlobalBean;
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

    public static RMDialog rmDialog(Context context, String text, String iconFont, int iconColor, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog)
                .setText(text)
                .setIcon(iconFont)
                .setIconColor(UIUtil.getInstance().getColor(iconColor))
                .setPositionBtn("OK", listener)
                .setNegativeBtn("Cancel", new RMDialog.OnNegativeClickListener() {
                    @Override
                    public void onNegativeClick() {

                    }
                });
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogNoCancel(Context context, String text, String iconFont, int iconColor, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(iconColor));
        dialog.setPositionBtn("OK", listener);
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogComfirm(Context context, String text, String iconFont, int color) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog)
                .setText(text)
                .setIcon(iconFont)
                .setIconColor(UIUtil.getInstance().getColor(color))
                .setPositionBtn("OK", new RMDialog.OnPositiveClickListener() {
                    @Override
                    public void onPositiveClick() {

                    }
                });
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogSubText(Context context, String text, String subText, String iconFont, int color, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(color));
        dialog.setSubText(subText);
        dialog.setPositionBtn("OK", listener);
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogSubTextComfirm(Context context, String text, String subText, String iconFont, int color) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(color));
        dialog.setSubText(subText);
        dialog.setPositionBtn("OK", new RMDialog.OnPositiveClickListener() {
            @Override
            public void onPositiveClick() {

            }
        });
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

    public static RMProgressDialog rmProgressDialog(Context context) {
        RMProgressDialog dialog = new RMProgressDialog(context);
        dialog.show();
        return dialog;
    }

    public static RMEditDialog rmEditDialog(Context context, String title, String hint, RMEditDialog.OnPositiveClickListener listener) {
        RMEditDialog dialog = new RMEditDialog(context)
                .setEditHint(hint)
                .setTitle(title)
                .setPositveBtn(listener);
        dialog.show();
        return dialog;
    }

    public static void successDialog(Context context, String msg, RMDialog.OnPositiveClickListener listener) {
        rmDialogNoCancel(context, msg, GlobalBean.SUCCESSFUL_ICON, R.color.green, listener);
    }

    public static void failDialog(Context context, String msg) {
        rmDialogComfirm(context, msg, GlobalBean.CANCEL_ICON, R.color.red);
    }

}
