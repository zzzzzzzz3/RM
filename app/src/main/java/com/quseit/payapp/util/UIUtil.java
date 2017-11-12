package com.quseit.payapp.util;

import android.content.Context;

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


    public static int dp2Px(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (scale*dp+0.5);
    }
}
