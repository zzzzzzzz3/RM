package com.quseit.payapp.boardcast;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import com.quseit.payapp.util.DownloadApkUtil;


/**
 * 文 件 名: DownloadUpdateReceiver
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/8/15 10:20
 * 邮    箱: qq798435167@gmail.com
 * 博    客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class DownloadUpdateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())&& DownloadApkUtil.downloadId>=0){
            Cursor cursor = null;
            long id = DownloadApkUtil.downloadId;
            try{
                DownloadManager.Query query  = new DownloadManager.Query();
                query.setFilterById(id);
                DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                cursor = dm.query(query);
                if (cursor.moveToFirst()){
                    int status  =  cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
                    if (status == DownloadManager.STATUS_FAILED){
                        dm.remove(id);
                    }else if (status == DownloadManager.STATUS_SUCCESSFUL){
                        if (DownloadApkUtil.apkPath!=null){
                            DownloadApkUtil.installApk(context,DownloadApkUtil.apkPath);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                if (cursor!=null){
                    cursor.close();
                }
            }
        }
    }
}
