package com.quseit.payapp.util;

/**
 * 文 件 名: CommonUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/10/25 10:47
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class CommonUtil {

    /**
     * 检查空字符
     * */
    public static boolean checkStr(String string){
        return string!=null && !string.equals("");
    }

    /**
     * 检查密码是否合法
     * */
    public static boolean checkPassword(String pass){
        return checkStr(pass);
    }
}
