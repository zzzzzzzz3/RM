package com.quseit.payapp.widget;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.quseit.payapp.R;

import static android.R.attr.duration;

/**
 * 文 件 名: RMToast
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/11 15:16
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class RMToast extends Toast {

    private ImageView icon;
    private TextView msgTv;
    private final View mView;
    private Context mContext;

    public enum TYPE{
        SUCCESS,FAILED
    }

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */
    public RMToast(Context context,String msg,TYPE type,int duration) {
        super(context);
        mContext = context;
        mView = LayoutInflater.from(context).inflate(R.layout.toast_rm,null);
        initView(mView);
        setView(mView);
        setGravity(Gravity.CENTER,0,0);
        setDuration(duration);
        msgTv.setText(msg);
        if (type == TYPE.FAILED){
            icon.setImageResource(R.mipmap.failed_icon);
        }else {
            icon.setImageResource(R.mipmap.success_icon);
        }
    }

    private void initView(View view) {
        icon = (ImageView) view.findViewById(R.id.toast_icon);
        msgTv = (TextView) view.findViewById(R.id.toast_text);
    }

    public static RMToast create(Context context,String msg,TYPE type,int duration){
        return new RMToast(context,msg,type,duration);
    }

    @Override
    public void show() {
        super.show();
    }
}
