package com.quseit.payapp.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 文 件 名: VersionUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/8/15 09:37
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class VersionUtil {

    public static String getVersionCode(Context context){
        PackageManager pm = context.getPackageManager();
        PackageInfo info = null;
        try {
            info = pm.getPackageInfo(context.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionName;
    }

    public static int[] parseVersion(String version){
        String[] versions = version.split("\\.");
        int[] a = new int[versions.length];
        for (int i = 0; i < versions.length; i++) {
            a[i] = Integer.parseInt(versions[i]);
        }
        return a;
    }

    public static boolean needUpdate(Context context,String version){
        try{
            String lv = getVersionCode(context);
            int[] localVersion = parseVersion(lv);
            int[] remoteVersion = parseVersion(version);

            for (int i = 0; i < localVersion.length; i++) {
                if (localVersion[i]>remoteVersion[i]){
                    return false;
                }
                if (localVersion[i]<remoteVersion[i]){
                    return true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
