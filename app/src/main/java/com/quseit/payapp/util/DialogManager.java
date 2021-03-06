package com.quseit.payapp.util;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.quseit.payapp.R;
import com.quseit.payapp.widget.RMComfirmDialog;
import com.quseit.payapp.widget.RMDialog;
import com.quseit.payapp.widget.RMEditDialog;


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


    public static RMDialog rmDialog(Context context, String text, String iconFont, int iconColor, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog)
                .setText(text)
                .setIcon(iconFont)
                .setIconColor(UIUtil.getInstance().getColor(iconColor))
                .setPositionBtn(listener);
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogComfirm(Context context, String text, String iconFont, int iconColor, RMDialog.OnComfirmClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(iconColor));
        dialog.setComfirmBtn(listener);
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogSubText(Context context, String text, String subText, String iconFont, int color, RMDialog.OnPositiveClickListener listener) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(color));
        dialog.setSubText(subText);
        dialog.setPositionBtn(listener);
        dialog.show();
        return dialog;
    }

    public static RMDialog rmDialogSubTextComfirm(Context context, String text, String subText, String iconFont, int color) {
        RMDialog dialog = new RMDialog(context, R.style.Dialog);
        dialog.setText(text);
        dialog.setIcon(iconFont);
        dialog.setIconColor(UIUtil.getInstance().getColor(color));
        dialog.setSubText(subText);
        dialog.setComfirmBtn(new RMDialog.OnComfirmClickListener() {

            @Override
            public void onComfirmClick() {

            }
        });
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
        RMComfirmDialog dialog = new RMComfirmDialog(context)
                .setIcon(context.getString(R.string.success_font))
                .setIconColor(ContextCompat.getColor(context,R.color.green))
                .setPositionBtn("OK",listener)
                .setText(msg);
                dialog.show(true);
    }

    public static void failDialog(Context context, String msg) {
        if (msg.equals("")){
            netErrorDialog(context);
        }else {
            RMComfirmDialog dialog = new RMComfirmDialog(context)
                    .setIcon(context.getString(R.string.fail_font))
                    .setIconColor(ContextCompat.getColor(context,R.color.red))
                    .setNegativeBtn("OK",null)
                    .setText(msg);
            dialog.show(false);
        }
    }

    public static void netErrorDialog(Context context) {
        RMComfirmDialog dialog = new RMComfirmDialog(context)
                .setIcon(context.getString(R.string.net_error_font))
                .setIconColor(ContextCompat.getColor(context,R.color.red))
                .setNegativeBtn("OK",null)
                .setText("Connection error");
        dialog.show(false);
    }

}
