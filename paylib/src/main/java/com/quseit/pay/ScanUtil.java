package com.quseit.pay;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.landicorp.android.scan.scanDecoder.ScanDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * 文 件 名: ScanUtil
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/10 09:53
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class ScanUtil {
    private ScanDecoder mScanDecoder;

    private static final String PAR_DEVELOP_TEST = "PAR_DEVELOP_TEST";
    private static final String PAR_SCAN_TIMEOUT = "PAR_SCAN_TIMEOUT";
    private Map<String,String> config;

    public ScanUtil(Context context) {
        config = new HashMap<>();
        mScanDecoder = new ScanDecoder(context);
    }

    public void beginScan(@NonNull Activity activity,@NonNull final ScanCallback resultCallback){
        int createCode = mScanDecoder.Create(ScanDecoder.CAMERA_ID_BACK, resultCallback);
        if (createCode==0){
            int startCode = mScanDecoder.startScanDecode(activity,config);
            if (startCode != 0){
                resultCallback.onError("ScanDecoder no create!");
            }
        }else {
            resultCallback.onError("Back camera fail to open!");
        }
    }

    public interface ScanCallback extends ScanDecoder.ResultCallback{
        void onError(String msg);
    }

    public void closeScan(){
        mScanDecoder.Destroy();
    }

}
