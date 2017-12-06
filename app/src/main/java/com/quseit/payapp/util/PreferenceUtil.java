package com.quseit.payapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 文 件 名: PreferenceUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/25 10:40
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class PreferenceUtil {
    private static PreferenceUtil instance = null;
    private SharedPreferences mPreferences;

    private PreferenceUtil(){

    }

    public void init(Context context){
        mPreferences = context.getSharedPreferences("data",Context.MODE_PRIVATE);
    }

    public static PreferenceUtil getInstance(){
        if (instance == null){
            synchronized (PreferenceUtil.class){
                instance = new PreferenceUtil();
            }
        }
        return instance;
    }

    public void saveStr(String key,String value){
        mPreferences.edit().putString(key,value).apply();
    }

    public String getStr(String key){
        return mPreferences.getString(key,"");
    }

    public void delete(String key){
        mPreferences.edit().remove(key).apply();
    }

    public void clear(){
        mPreferences.edit().clear().apply();
    }
}
