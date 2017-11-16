package com.quseit.payapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.quseit.payapp.bean.GlobalBean;
import com.quseit.payapp.bussiness.devicesetting.DeviceSettingActivity;
import com.quseit.payapp.bussiness.main.MainActivity;
import com.quseit.payapp.util.DataStore2;
import com.quseit.payapp.util.PreferenceUtil;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Timer;
import java.util.TimerTask;

import javax.crypto.NoSuchPaddingException;

/**
 * 文 件 名: WelcomeActivity
 * 创 建 人: ZhangRonghua
 * 创建日期: 2017/11/9 20:27
 * 邮   箱: qq798435167@gmail.com
 * 修改时间：
 * 修改备注：
 */

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                String token = DataStore2.getInstance().getData(GlobalBean.DECIVE_TOKEN);
                if (token!=null&&!token.equals("")){
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                }else {
                    startActivity(new Intent(WelcomeActivity.this,DeviceSettingActivity.class));
                }
                finish();
            }
        },1000);
    }
}
