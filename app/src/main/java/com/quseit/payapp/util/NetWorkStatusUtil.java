package com.quseit.payapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;

/**
 * 文 件 名: NetWorkStatusUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/7/22 19:17
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class NetWorkStatusUtil {
    private static NetWorkStatusUtil instance = null;
    private ConnectivityManager mConnectivityManager;

    private NetWorkStatusUtil() {

    }

    public static NetWorkStatusUtil getInstance() {
        if (instance == null) {
            synchronized (NetWorkStatusUtil.class) {
                if (instance == null) {
                    instance = new NetWorkStatusUtil();
                }
            }
        }

        return instance;
    }

    public void init(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public interface OnNetWorkStatusChangeListener {
        void onLine();

        void offLine();
    }

    @SuppressWarnings("deprecation")
    public void check(OnNetWorkStatusChangeListener listener) {
        //检测API是不是小于23，因为到了API23之后getNetworkInfo(int networkType)方法被弃用
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {

            //获取ConnectivityManager对象对应的NetworkInfo对象
            //获取WIFI连接的信息
            NetworkInfo wifiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            //获取移动数据连接的信息
            NetworkInfo dataNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if (!wifiNetworkInfo.isConnected() && !dataNetworkInfo.isConnected()) {
                if (listener != null) {
                    listener.offLine();
                }
            } else {
                if (listener != null) {
                    listener.onLine();
                }
            }
            //API大于23时使用下面的方式进行网络监听
        } else {

            //获取所有网络连接的信息
            Network[] networks = mConnectivityManager.getAllNetworks();
            //判断是否有网络
            boolean isConnect = false;
            //通过循环将网络信息逐个取出来
            for (int i = 0; i < networks.length; i++) {
                //获取ConnectivityManager对象对应的NetworkInfo对象
                NetworkInfo networkInfo = mConnectivityManager.getNetworkInfo(networks[i]);
                if (networkInfo.isConnected()) {
                    isConnect = true;
                    break;
                }
            }
            if (isConnect) {
                if (listener != null) {
                    listener.onLine();
                }
            } else {
                if (listener != null) {
                    listener.offLine();
                }
            }
        }
    }
}
