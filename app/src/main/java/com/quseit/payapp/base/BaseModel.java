package com.quseit.payapp.base;


import android.util.Base64;

import com.quseit.payapp.Http.CommonService;
import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.DataStore2;
import com.quseit.payapp.util.PreferenceUtil;

import okio.ByteString;

/**
 * 文 件 名: BaseModel
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/27 11:52
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public abstract class BaseModel {
    public static final String HEADER_ = "Bearer ";
    public String mToken;

    public BaseModel(){
        mToken = HEADER_+DataStore2.getInstance().getData(GlobalBean.DECIVE_TOKEN);
//        mToken = HEADER_+"voS2IAf_3SmfIu2FgqFR8qhYFkCadnSmGYRFPdIgrO0";
    }
}
