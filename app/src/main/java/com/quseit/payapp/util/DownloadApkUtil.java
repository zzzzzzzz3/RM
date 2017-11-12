package com.quseit.payapp.util;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 文 件 名: DownloadApkUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/8/15 09:52
 * 邮   箱: qq798435167@gmail.com
 * 博   客: http://zzzzzzzz3.github.io
 * 修改时间：
 * 修改备注：
 */

public class DownloadApkUtil {

    public static long downloadId = -1;
    public static String apkPath;


    public static void downloadByWeb(Context context,String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


    public static void downloadByAPP(Context context,String url,String saveDir,String title){
        try {
            Uri downloadUri = Uri.parse(url);
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(downloadUri);
            request.setVisibleInDownloadsUi(true);
            request.setTitle(title);

            initApkPath(url, saveDir);
            File file = new File(apkPath);
            if (file.exists()){
                file.delete();
            }
            Uri fileUri = Uri.parse("file://"+apkPath);
            request.setDestinationUri(fileUri);
            downloadId = downloadManager.enqueue(request);
        }catch (Exception e){
            e.printStackTrace();
            downloadByWeb(context,url);
        }
    }
    
    public static void downloadByHttp(final String url, final String saveDir, final OnDownloadListener listener){
        // TODO: 2017/8/15 使用okhttp下载
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        final Handler handler = new Handler(Looper.getMainLooper());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onError("网络错误");
                    }
                });
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                InputStream is = null;
                FileOutputStream fos = null;
                byte[] buff = new byte[2048];
                int len = -1;
                try {
                    initApkPath(url,saveDir);
                    long total = response.body().contentLength();
                    float fraction = 1.0f/total*100;
                    long sum = 0;
                    File file = new File(apkPath);
                    if (!file.exists()){
                        file.createNewFile();
                    }
                    fos = new FileOutputStream(file);
                    is = response.body().byteStream();
                    while ((len = is.read(buff))!=-1){
                        fos.write(buff,0,len);
                        sum+=len;
                        final int progress = (int) (sum*fraction);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.onLoading(progress);
                            }
                        });
                    }
                    fos.flush();
                    listener.onComplete();
                } catch (Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onError("网络错误");
                        }
                    });
                }finally {
                    if (is!=null){
                        is.close();
                    }
                    if (fos!=null){
                        fos.close();
                    }
                }
            }
        });
    }

    private static void initApkPath(String url, String saveDir) throws Exception {
        String fileDir = null;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            fileDir = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+saveDir;
            File dir = new File(fileDir);
            if (!dir.exists()){
                dir.mkdirs();
            }
        }else {
            throw new Exception("没有sd卡");
        }
        apkPath = fileDir + File.separator+getNameFromUrl(url);
    }

    public static String getNameFromUrl(String url){
        return url.substring(url.lastIndexOf("/")+1);
    }

    public static void installApk(Context context,String filePath){
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://"+filePath),"application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    public interface OnDownloadListener{
        void onError(String msg);
        void onLoading(int progress);
        void onComplete();
    }

}
