package com.quseit.payapp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

/**
 * 文 件 名: UIUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 17:47
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class UIUtil {

    private static UIUtil instance = null;
    private float mScale;
    private Context mContext;
    private int mScreenWith;
    private int mScreenHeight;

    private UIUtil(Context context){
        mContext = context;
        mScale = mContext.getResources().getDisplayMetrics().density;
        mScreenWith = mContext.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight = mContext.getResources().getDisplayMetrics().heightPixels;
    }

    public static void init(Context context){
        instance = new UIUtil(context);
    }

    public static UIUtil getInstance(){
        return instance;
    }

    public int dp2px(int dp){
        return (int) (mScale*dp+0.5);
    }

    public int getColor(int colorRes){
        return ContextCompat.getColor(mContext,colorRes);
    }

    public String getString(int strRes){
        return mContext.getString(strRes);
    }

    public Drawable getDrawable(int resId){
        return ContextCompat.getDrawable(mContext,resId);
    }



    public static int dp2Px(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale*dp+0.5);
    }
}
