package com.quseit.payapp.base;


import com.quseit.payapp.Http.CommonService;
import com.quseit.dev.RetrofitManager;
import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.util.PreferenceUtil;

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
    public CommonService mCommonService;
    public String mToken;
    public String mUserId;

    public BaseModel(){
        mCommonService = RetrofitManager.getInstance().createService(CommonService.class);
        mToken = PreferenceUtil.getInstance().getStr(GlobalBean.TOKEN);
        mUserId = PreferenceUtil.getInstance().getStr(GlobalBean.USER_ID);
    }
}
