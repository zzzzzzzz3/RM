package com.quseit.payapp.util;

import android.app.Activity;
import android.content.Context;

import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;

import io.reactivex.functions.Consumer;

/**
 * 文 件 名: PermissionUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/8 21:01
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class PermissionUtil {
    public static void requestPermissions(Activity activity, String permissions, final RequestPermissionCallback callback) {
        RxPermissions rxPermission = new RxPermissions(activity);
        rxPermission
                .requestEach(permissions)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            callback.onGranted();
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            callback.onUnGranted();
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            callback.onUnGranted();
                        }
                    }
                });
    }

    public interface RequestPermissionCallback {
        //授予权限
        void onGranted();

        //没授予权限
        void onUnGranted();
    }
}
