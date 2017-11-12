package com.quseit.payapp.base;

/**
 * 文 件 名: IActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 13:53
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public interface IActivity {
    /**
     * 返回布局id
     * */
    int getRootView();
    /**
     * 对控件进行初始化
     * */
    void initView();
    /**
     * 请求数据
     * */
    void initData();
    /**
     * 返回标题
     * */
    String getToolbarTitle();
}
