package com.quseit.payapp.util;

import android.widget.TextView;

/**
 * 文 件 名: AmountInputUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/11 00:24
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class AmountInputUtil {

    /**
     * 删除单个数字
     */
    public static String deleteNum(String str, boolean hasDot) {
        //不包含其他数字直接返回
        if (!includeOther(str.toCharArray())) {
            return str;
        }
        //只有三个数字和小数点时
        if (str.length() <= (hasDot?4:1)) {
            //去掉最后一个数
            str = str.substring(0, str.length() - 1);
            //在前面插入0
            str = "0" + str;
            //然后移动小数点
            str = moveDotL(str.toCharArray());
        } else {
            //包含四个数字时直接去掉最后一位然后移动小数点
            str = str.substring(0, str.length() - 1);
            str = moveDotL(str.toCharArray());
        }
        return str;
    }

    /**
     * 向左移动小数点
     * */
    private static String moveDotL(char[] list) {
        for (int i = 1, len = list.length; i < len - 1; i++) {
            if (list[i] == '.') {
                char temp = list[i];
                list[i] = list[i - 1];
                list[i - 1] = temp;
                break;
            }
        }
        return new String(list);
    }

    /**
     * 输入数字
     */
    public static String input(String insert,String origin) {
        //1.限制输入12位
        //2.输入为零且不包含其他数字直接返回
        if (origin.length() > 12 || (insert.equals("0")&&!includeOther(origin.toCharArray()))) {
            return origin;
        }
        //如果首位字符为零则直接删除
        if (origin.charAt(0) == '0') {
            origin = origin.substring(1, origin.length());
        }
        //向当前字符串尾部直接加上点击的数字
        origin += insert;
        //然后向右移动小数点
        origin = moveDotR(origin.toCharArray());
        return origin;
    }

    /**
     * 是否包含除小数点和0之外的数
     * */
    private static boolean includeOther(char[] list) {
        for (int i = 0, len = list.length; i < len; i++) {
            char c = list[i];
            if (c != '0' && c != '.') {
                return true;
            }
        }
        return false;
    }


    /**
     * 向右移动小数点
     * */
    private static String moveDotR(char[] list) {
        for (int i = 0, len = list.length; i < len - 1; i++) {
            if (list[i] == '.') {
                char temp = list[i];
                list[i] = list[i + 1];
                list[i + 1] = temp;
                break;
            }
        }
        return new String(list);
    }
}
