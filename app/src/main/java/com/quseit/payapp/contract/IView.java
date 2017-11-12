package com.quseit.payapp.contract;

/**
 * 文 件 名: IView
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/24 15:00
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public interface IView {
    /**
     * 显示加载
     */
    void showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 显示信息
     */
    void showMessage(String message);

    /**
     * 杀死自己
     */
    void killMyself();
}
